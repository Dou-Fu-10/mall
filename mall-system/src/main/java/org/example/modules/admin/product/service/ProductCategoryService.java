package org.example.modules.admin.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.product.entity.ProductCategoryEntity;
import org.example.modules.admin.product.entity.dto.ProductCategoryDto;
import org.example.modules.admin.product.entity.vo.ProductCategoryVo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


/**
 * Created by Dou-Fu-10 2023-07-13 22:08:15
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 22:08:15
 * @Description 产品分类(ProductCategory)表服务接口
 */
public interface ProductCategoryService extends IService<ProductCategoryEntity> {

    /**
     * 通过分类名字查询分类信息
     *
     * @param categoryName 分类名字
     * @return 分类信息
     */
    ProductCategoryEntity getByCategoryName(String categoryName);

    /**
     * 通过分类id查询分类信息
     *
     * @param id 分类名字
     * @return 分类信息
     */
    ProductCategoryEntity getByCategoryId(Long id);

    /**
     * 新增数据
     *
     * @param productCategory 实体对象
     * @return 新增结果
     */
    Boolean save(ProductCategoryDto productCategory);

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param productCategory 查询实体
     * @return 所有数据
     */
    Page<ProductCategoryVo> page(Page<ProductCategoryEntity> page, ProductCategoryEntity productCategory);

    /**
     * 修改数据
     *
     * @param productCategory 实体对象
     * @return 修改数据
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    Boolean updateById(ProductCategoryDto productCategory);

    /**
     * 修改导航栏显示状态
     *
     * @param idList    修改状态的id
     * @param navStatus 显示状态
     * @return String
     */
    Boolean updateNavStatus(Set<Long> idList, Boolean navStatus);

    /**
     * 修改显示状态
     *
     * @param idList     修改状态的id
     * @param showStatus 显示状态
     * @return String
     */
    Boolean updateShowStatus(Set<Long> idList, Boolean showStatus);

}
