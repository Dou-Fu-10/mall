package org.example.modules.portal.product.serveice;

import org.example.modules.admin.product.entity.ProductCategoryEntity;
import org.example.modules.admin.product.entity.vo.ProductCategoryVo;
import org.example.modules.portal.product.entity.vo.PublicProductCategoryVo;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-27 14:08:15
 *
 * @author Dou-Fu-10
 * @date 2023-07-27 14:08:15
 * @Description 产品分类(ProductCategory)表服务接口
 */
public interface PublicProductCategoryService {
    List<PublicProductCategoryVo> selectProductCategory(ProductCategoryEntity productCategory);
}
