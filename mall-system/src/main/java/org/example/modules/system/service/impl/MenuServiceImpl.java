package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.RolesMenusRelationEntity;
import org.example.modules.system.entity.dto.MenuDto;
import org.example.modules.system.entity.vo.MenuMetaVo;
import org.example.modules.system.entity.vo.MenuVo;
import org.example.modules.system.mapper.MenuMapper;
import org.example.modules.system.service.MenuService;
import org.example.modules.system.service.RoleService;
import org.example.modules.system.service.RolesMenusRelationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:13
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:13
 * @Description 后台菜单表(Menu)表服务实现类
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Resource
    private RoleService roleService;
    @Resource
    private RolesMenusRelationService rolesMenusRelationService;

    @Override
    public Boolean updateHidden(Long id, Boolean hidden) {
        if (Objects.isNull(id) || Objects.isNull(hidden)) {
            return false;
        }
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(id);
        menuEntity.setHidden(hidden);
        return menuEntity.updateById();
    }

    @Override
    public List<MenuVo> findByUser(Long adminId) {
        if (Objects.isNull(adminId)) {
            throw new BaseRequestException("获取失败");
        }
        // 获取用户对应的角色
        List<RoleEntity> roles = roleService.findByUsersId(adminId);
        if (CollectionUtils.isEmpty(roles)) {
            return new ArrayList<>();
        }
        // 获取角色id
        Set<Long> roleIds = roles.stream().map(RoleEntity::getId).collect(Collectors.toSet());

        // 获取角色对应的菜单
        List<RolesMenusRelationEntity> rolesMenusRelationEntityList = rolesMenusRelationService.findByRoleIdsAndTypeNot(roleIds);
        if (CollectionUtils.isEmpty(rolesMenusRelationEntityList)) {
            return new ArrayList<>();
        }
        Set<Long> menuId = rolesMenusRelationEntityList.stream().map(RolesMenusRelationEntity::getMenuId).collect(Collectors.toSet());
        List<MenuEntity> menus = lambdaQuery().in(MenuEntity::getId, menuId).ne(MenuEntity::getType, 2L).list();
        // getMenuVoListTree 会判断是否为空 为空即返回空
        return getMenuVoListTree(BeanCopy.copytList(menus, MenuVo.class));
    }

    @Override
    public List<MenuVo> findByMenusId(Set<Long> menusId) {
        // 判断是否为空
        if (CollectionUtils.isEmpty(menusId)) {
            return new ArrayList<>();
        }
        // 获取菜单列表
        List<MenuEntity> menuEntityList = lambdaQuery().in(MenuEntity::getId, menusId).list();
        // 判断是否为空
        if (CollectionUtils.isEmpty(menuEntityList)) {
            return new ArrayList<>();
        }
        List<MenuVo> menuVoList = BeanCopy.copytList(menuEntityList, MenuVo.class);
        // getMenuVoListTree 会判断是否为空 为空即返回空
        return getMenuVoListTree(menuVoList);
    }

    @Override
    public MenuVo getByMenuId(Serializable id) {
        if (Objects.isNull(id)) {
            return null;
        }
        MenuEntity menuEntity = getById(id);
        return BeanCopy.convert(menuEntity, MenuVo.class);
    }

    @Override
    public Boolean save(MenuDto menuDto) {
        MenuEntity menuEntity = BeanCopy.convert(menuDto, MenuEntity.class);
        String title = menuEntity.getTitle();
        // 校验标题
        if (Objects.nonNull(getTitle(title))) {
            throw new BaseRequestException("标题输入错误或者标题已被占用");
        }
        return menuEntity.insert();
    }

    @Override
    public MenuEntity getTitle(@NotNull String title) {
        return lambdaQuery().eq(MenuEntity::getTitle, title).one();
    }

    @Override
    public Boolean updateById(MenuDto menuDto) {
        MenuEntity menuEntity = BeanCopy.convert(menuDto, MenuEntity.class);
        return menuEntity.updateById();
    }

    @Override
    public List<MenuVo> getOneLevelMenu() {
        // 查询所有 一级分类
        List<MenuEntity> list = lambdaQuery().eq(MenuEntity::getParentId, 0L).list();
        return BeanCopy.copytList(list, MenuVo.class);
    }

    @Override
    public Page<MenuVo> page(Page<MenuEntity> page, MenuDto menuDto) {
        MenuEntity menuEntity = BeanCopy.convert(menuDto, MenuEntity.class);
        LambdaQueryWrapper<MenuEntity> menuEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(menuEntity);
        // 排序
        menuEntityLambdaQueryWrapper.orderByAsc(MenuEntity::getSort);
        Page<MenuEntity> menuEntityPage = page(page, menuEntityLambdaQueryWrapper);
        // 转换
        IPage<MenuVo> menuVoIpage = menuEntityPage.convert(menu -> BeanCopy.convert(menu, MenuVo.class));
        // 当菜单不为空是 对菜单进行 上下级排序
        if (CollectionUtils.isNotEmpty(menuVoIpage.getRecords())) {
            List<MenuVo> menuVoListTree = getMenuVoListTree(menuVoIpage.getRecords());
            menuVoIpage.setRecords(menuVoListTree);
        }
        return (Page<MenuVo>) menuVoIpage;
    }


    @NotNull
    private List<MenuVo> getMenuVoListTree(List<MenuVo> menuVo) {
        // 1.首先，判断输入的类别集合是否为空，如果为空，则立即返回一个空的列表。
        if (CollectionUtils.isEmpty(menuVo)) {
            return new ArrayList<>();
        }
        // 以父类id为key，将自己为value添加到父类id名下的集合里面
        // 2.创建三个 Map 变量，分别用于存储类别信息、父子关系信息。categoryMap 存储类别 ID 与对应的 ProductCategoryVo 对象，parentMap 存储每个类别节点的所有子节点。
        Map<Long, List<MenuVo>> parentMap = new HashMap<>(menuVo.size());
        // 循环集合
        // 3.对于输入的每个类别对象，设置它的 children 属性为一个空的列表；将它加入到 categoryMap 中，并将它的父子关系添加到 parentMap 中。父子关系的构建通过调用computeIfAbsent方法实现。
        menuVo.forEach(category -> {
            // 创建一个空子类集合集结地
            category.setChildren(new ArrayList<>());
            MenuMetaVo menuMetaVo = new MenuMetaVo();
            if (StringUtils.isNotBlank(category.getTitle())) {
                menuMetaVo.setTitle(category.getTitle());
            }
            if (StringUtils.isNotBlank(category.getIcon())) {
                menuMetaVo.setIcon(category.getIcon());
            }
            category.setMenuMeta(menuMetaVo);
            // 判断 category.getPid()（即当前被循环到的ProductCategoryVo他的父类Pid） 是否在parentMap（Pid最为kay）里面存在，
            // 如果存在取出对应的value（这里是一个List<ProductCategoryVo>集合）然后将自身（以add的方式即add(category)）存进去，
            // 如果不存在就调用 lambda 表达式得出一个新（List<ProductCategoryVo>）集合，然后将自身（以add的方式即add(category)）存进去，
            parentMap.computeIfAbsent(category.getParentId(), key -> new ArrayList<>()).add(category);
        });
        // 存放顶级（父类）节点的集合
        // 4.创建一个链表类型的 resultList 变量，用于保存类别树的根节点列表。
        List<MenuVo> resultList = new LinkedList<>();
        // 5.再次遍历所有类别对象，找到每个父节点，将它们加入到 resultList 中。
        menuVo.forEach(root -> {
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
            Deque<MenuVo> stack = new ArrayDeque<>();
            //ArrayDeque 是一种双端队列数据结构，支持在队列的两端进行插入、删除、访问元素等操作。在实际应用中，ArrayDeque 通常用于实现栈或队列等数据结构，也可以用于实现一些基本的数据结构，如 List、Queue 等。
            stack.push(root);
            while (!stack.isEmpty()) {
                // 7.从栈中弹出一个节点，通过该节点的 ID 从 parentMap 中查找它的子节点列表，将子节点添加到该节点的 children 列表中，并将子节点入栈。
                //pop() 方法会改变原始的 ArrayDeque。调用 pop() 方法会从 ArrayDeque 的头部删除一个元素，并返回被删除的元素，同时修改 ArrayDeque 的大小和顺序。
                MenuVo node = stack.pop();
                //在 Map 接口中，执行 remove() 方法时会返回被删除键所对应的值。具体来说，remove(Object key) 方法的返回值为被删除键所对应的值，如果不存在对应的映射，则返回 null。
                // 即以key的方式删除 parentMap里的顶级类 并将被删除的（value）集合返回
                List<MenuVo> children = parentMap.remove(node.getId());
                // 判断不为空
                if (CollectionUtils.isNotEmpty(children)) {
                    children.forEach(child -> {
                        node.getChildren().add(child);
                        stack.push(child);
                    });
                }
            }
        });
        List<MenuVo> allValues = new ArrayList<>();
        for (Long key : parentMap.keySet()) {
            List<MenuVo> values = parentMap.get(key);
            if (values != null) {
                allValues.addAll(values);
            }
        }
        return allValues;
    }
}

