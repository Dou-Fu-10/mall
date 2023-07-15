package org.example.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
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

}

