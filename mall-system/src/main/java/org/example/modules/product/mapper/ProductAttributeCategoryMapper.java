package org.example.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.modules.product.entity.ProductAttributeCategoryEntity;

import java.util.List;

/**
 * Created by PanShiFu 2023-07-14 11:03:42
 *
 * @author PanShiFu
 * @date 2023-07-14 11:03:42
 * @Description 产品属性分类表(ProductAttributeCategory)表数据库访问层
 */
@Mapper
public interface ProductAttributeCategoryMapper extends BaseMapper<ProductAttributeCategoryEntity> {
    /**
     * 获取包含属性的商品属性分类
     *
     * @return ProductAttributeCategoryEntity
     */
    @Select("SELECT " +
            "  pac.id," +
            "  pac.name," +
            "  pa.id attr_id," +
            "  pa.name attr_name " +
            "FROM " +
            "  pms_product_attribute_category pac " +
            "  LEFT JOIN pms_product_attribute pa " +
            "    ON pac.id = pa.product_attribute_category_id " +
            "    AND pa.type = 1 ;" +
            "")
    List<ProductAttributeCategoryEntity> getListWithAttr();
}

