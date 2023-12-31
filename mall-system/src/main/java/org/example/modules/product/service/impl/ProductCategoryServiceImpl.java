package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.server.MinioServer;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.modules.product.entity.ProductCategoryEntity;
import org.example.modules.product.entity.dto.ProductCategoryDto;
import org.example.modules.product.entity.vo.ProductCategoryVo;
import org.example.modules.product.mapper.ProductCategoryMapper;
import org.example.modules.product.service.ProductCategoryAttributeRelationService;
import org.example.modules.product.service.ProductCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-13 22:08:15
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 22:08:15
 * @Description 产品分类(ProductCategory)表服务实现类
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryEntity> implements ProductCategoryService {
    @Resource
    private MinioServer minioServer;
    @Resource
    private ProductCategoryAttributeRelationService productCategoryAttributeRelationService;

    @Override
    public ProductCategoryEntity getByCategoryName(String categoryName) {
        if (Objects.isNull(categoryName)) {
            throw new BaseRequestException("参数有误");
        }
        return lambdaQuery().eq(ProductCategoryEntity::getName, categoryName).one();
    }

    @Override
    public ProductCategoryEntity getByCategoryId(Long id) {
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数有误");
        }
        return lambdaQuery().eq(ProductCategoryEntity::getId, id).one();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean save(ProductCategoryDto productCategory) {
        ProductCategoryEntity convert = BeanCopy.convert(productCategory, ProductCategoryEntity.class);

        // 确保 分类名唯一
        if (Objects.nonNull(getByCategoryName(convert.getName()))) {
            throw new BaseRequestException("分类名不唯一");
        }
        // 校验 上级分类id
        if (Objects.nonNull(convert.getParentId())) {
            if (Objects.isNull(getByCategoryId(convert.getParentId()))) {
                throw new BaseRequestException("正确的填写上级分类");
            }
        }
        // 判断是否上传图片
        if (StringUtils.isNotBlank(convert.getIcon())) {
            if (!minioServer.checkObjectIsExist(convert.getIcon())) {
                throw new BaseRequestException("请上传正确的文件");
            }
        }
        if (!convert.insert()) {
            throw new BaseRequestException("添加失败");
        }

        assert productCategory != null;
        // 商品属性参数表
        if (CollectionUtils.isNotEmpty(productCategory.getProductAttributeIdList())) {
            if (!productCategoryAttributeRelationService.save(convert.getId(), productCategory.getProductAttributeIdList())) {
                throw new BaseRequestException("绑定属性失败");
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateById(ProductCategoryDto productCategory) {
        ProductCategoryEntity convert = BeanCopy.convert(productCategory, ProductCategoryEntity.class);

        // 校验 上级分类id
        if (Objects.nonNull(convert.getParentId())) {
            if (Objects.isNull(getByCategoryId(convert.getParentId()))) {
                throw new BaseRequestException("正确的填写上级分类");
            }
        }
        // 名字 是否改变
        if (Objects.nonNull(lambdaQuery().eq(ProductCategoryEntity::getName, convert.getName()).ne(ProductCategoryEntity::getId, productCategory.getId()).one())) {
            throw new BaseRequestException("分类名不唯一");
        }

        // 判断是否上传图片
        if (Objects.nonNull(convert.getIcon())) {
            if (!minioServer.checkObjectIsExist(convert.getIcon())) {
                throw new BaseRequestException("请上传正确的文件");
            }
        }

        if (!convert.updateById()) {
            throw new BaseRequestException("修改属性失败");
        }
        if (CollectionUtils.isNotEmpty(productCategory.getProductAttributeIdList())) {
            if (!productCategoryAttributeRelationService.updateByProductCategoryId(convert.getId(), productCategory.getProductAttributeIdList())) {
                throw new BaseRequestException("修改分类与属性的绑定失败");
            }
        }
        return true;
    }

    @Override
    public Page<ProductCategoryVo> page(Page<ProductCategoryEntity> page, ProductCategoryEntity productCategory) {
        LambdaQueryWrapper<ProductCategoryEntity> productCategoryEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(productCategory);
        // 排序
        productCategoryEntityLambdaQueryWrapper.orderByAsc(ProductCategoryEntity::getSort);
        Page<ProductCategoryEntity> productCategoryEntityPage = page(page, productCategoryEntityLambdaQueryWrapper);
        IPage<ProductCategoryVo> productCategoryVoIPage = productCategoryEntityPage.convert(productCategoryEntity -> BeanCopy.convert(productCategoryEntity, ProductCategoryVo.class));
        List<ProductCategoryVo> productCategoryVoList = productCategoryVoIPage.getRecords();
        // 当分类为空时 直接返回
        if (CollectionUtils.isEmpty(productCategoryVoList)) {
            return (Page<ProductCategoryVo>) productCategoryVoIPage;
        }
        // 对商品分类进行上下级的排序
        List<ProductCategoryVo> productCategoryVoListTree = getProductCategoryVoListTree(productCategoryVoList);
        // 返回排序好的信息
        productCategoryVoIPage.setRecords(productCategoryVoListTree);
        return (Page<ProductCategoryVo>) productCategoryVoIPage;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateNavStatus(Set<Long> idList, Boolean navStatus) {
        // 数据校验
        if (CollectionUtils.isEmpty(idList) || Objects.isNull(navStatus)) {
            throw new BaseRequestException("参数错误");
        }
        Set<ProductCategoryEntity> collect = idList.stream().map(id -> new ProductCategoryEntity(id, navStatus, null)).collect(Collectors.toSet());
        // 当其中一个更新失败后数据回滚
        return updateBatchById(collect);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateShowStatus(Set<Long> idList, Boolean showStatus) {
        // 数据校验
        if (CollectionUtils.isEmpty(idList) || Objects.isNull(showStatus)) {
            throw new BaseRequestException("参数错误");
        }
        Set<ProductCategoryEntity> collect = idList.stream().map(id -> new ProductCategoryEntity(id, null, showStatus)).collect(Collectors.toSet());
        // 当其中一个更新失败后数据回滚
        return updateBatchById(collect);
    }

    @Override
    public ProductCategoryVo getByProductCategoryId(Serializable productCategoryId) {
        if (Objects.isNull(productCategoryId)) {
            return null;
        }
        ProductCategoryEntity productCategoryEntity = getById(productCategoryId);
        return BeanCopy.convert(productCategoryEntity, ProductCategoryVo.class);
    }

    @org.jetbrains.annotations.NotNull
    private List<ProductCategoryVo> getProductCategoryVoListTree(List<ProductCategoryVo> productCategoryVoList) {
        // 1.首先，判断输入的类别集合是否为空，如果为空，则立即返回一个空的列表。
        if (CollectionUtils.isEmpty(productCategoryVoList)) {
            return new ArrayList<>();
        }
        // 以父类id为key，将自己为value添加到父类id名下的集合里面
        // 2.创建三个 Map 变量，分别用于存储类别信息、父子关系信息。categoryMap 存储类别 ID 与对应的 ProductCategoryVo 对象，parentMap 存储每个类别节点的所有子节点。
        Map<Long, List<ProductCategoryVo>> parentMap = new HashMap<>(productCategoryVoList.size());
        // 循环集合
        // 3.对于输入的每个类别对象，设置它的 children 属性为一个空的列表；将它加入到 categoryMap 中，并将它的父子关系添加到 parentMap 中。父子关系的构建通过调用computeIfAbsent方法实现。
        productCategoryVoList.forEach(category -> {
            // 创建一个空子类集合集结地
            category.setChildren(new ArrayList<>());
            // 判断 category.getPid()（即当前被循环到的ProductCategoryVo他的父类Pid） 是否在parentMap（Pid最为kay）里面存在，
            // 如果存在取出对应的value（这里是一个List<ProductCategoryVo>集合）然后将自身（以add的方式即add(category)）存进去，
            // 如果不存在就调用 lambda 表达式得出一个新（List<ProductCategoryVo>）集合，然后将自身（以add的方式即add(category)）存进去，
            parentMap.computeIfAbsent(category.getParentId(), key -> new ArrayList<>()).add(category);
        });
        // 存放顶级（父类）节点的集合
        // 4.创建一个链表类型的 resultList 变量，用于保存类别树的根节点列表。
        List<ProductCategoryVo> resultList = new LinkedList<>();
        // 5.再次遍历所有类别对象，找到每个父节点，将它们加入到 resultList 中。
        productCategoryVoList.forEach(root -> {
            if (root.getParentId() == 0L) {
                resultList.add(root);
            }
        });
        // forEach 类似 类似 for 里面的操作会改变自身
        resultList.forEach(root -> {
            //ArrayDeque 和 ArrayList 都是 Java 集合框架中提供的 List 类实现，但它们在内部的数据结构和使用上存在一些区别：
            //数据结构实现方式
            //ArrayDeque 内部使用基于数组的数据结构实现，而 ArrayList 也是基于数组的实现。不同之处在于，ArrayDeque 实现了双端队列，即支持快速从队列头和队列尾添加或删除元素，而 ArrayList 只能在数组的尾部添加或删除元素。
            //空间性能
            //ArrayDeque 和 ArrayList 都是动态数组，但是 ArrayDeque 中的元素是被分散存储在数组中的，而 ArrayList 中的元素是连续存储的，这导致在增加和删除元素时，ArrayDeque 往往比 ArrayList 更节省空间。
            //访问性能
            //在访问值方面，ArrayList 要优于 ArrayDeque，因为 ArrayList 中的元素是连续存储的，因此具有更好的缓存局部性，而 ArrayDeque 中的元素是被分散存储的，访问效率相对更低。
            //线程安全
            //ArrayDeque 和 ArrayList 都不是线程安全的，因此在多线程环境中应该采用同步方式进行访问。
            //综上所述，如果需要频繁执行元素的插入和删除操作，并且需要从队列头和尾部进行操作，则应该使用 ArrayDeque，而如果需要在列表中进行频繁的随机访问，则应该使用 ArrayList。

            // 6.对于每个父节点，创建一个空的 stack，将该父节点压入栈中。
            Deque<ProductCategoryVo> stack = new ArrayDeque<>();
            //ArrayDeque 是一种双端队列数据结构，支持在队列的两端进行插入、删除、访问元素等操作。在实际应用中，ArrayDeque 通常用于实现栈或队列等数据结构，也可以用于实现一些基本的数据结构，如 List、Queue 等。
            stack.push(root);
            while (!stack.isEmpty()) {
                // 7.从栈中弹出一个节点，通过该节点的 ID 从 parentMap 中查找它的子节点列表，将子节点添加到该节点的 children 列表中，并将子节点入栈。
                //pop() 方法会改变原始的 ArrayDeque。调用 pop() 方法会从 ArrayDeque 的头部删除一个元素，并返回被删除的元素，同时修改 ArrayDeque 的大小和顺序。
                ProductCategoryVo node = stack.pop();
                //在 Map 接口中，执行 remove() 方法时会返回被删除键所对应的值。具体来说，remove(Object key) 方法的返回值为被删除键所对应的值，如果不存在对应的映射，则返回 null。
                // 即以key的方式删除 parentMap里的顶级类 并将被删除的（value）集合返回
                List<ProductCategoryVo> children = parentMap.remove(node.getId());
                // 判断不为空
                if (CollectionUtils.isNotEmpty(children)) {
                    children.forEach(child -> {
                        node.getChildren().add(child);
                        stack.push(child);
                    });
                }
            }
        });
        List<ProductCategoryVo> allValues = new ArrayList<>();
        for (Long key : parentMap.keySet()) {
            List<ProductCategoryVo> values = parentMap.get(key);
            if (values != null) {
                allValues.addAll(values);
            }
        }
        return allValues;
    }
}

