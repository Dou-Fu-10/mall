package org.example.modules.portal.product.serveice.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.product.entity.ProductCategoryEntity;
import org.example.modules.admin.product.entity.vo.ProductCategoryVo;
import org.example.modules.admin.product.service.ProductCategoryService;
import org.example.modules.portal.product.entity.vo.PublicProductCategoryVo;
import org.example.modules.portal.product.serveice.PublicProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-27 14:08:15
 *
 * @author Dou-Fu-10
 * @date 2023-07-27 14:08:15
 * @Description 产品分类(ProductCategory)表服务接口
 */
@Service("PublicProductCategoryService")
public class PublicProductCategoryServiceImpl implements PublicProductCategoryService {

    @Resource
    private ProductCategoryService productCategoryService;


    @Override
    public List<PublicProductCategoryVo> selectProductCategory(ProductCategoryEntity productCategory) {
        Page<ProductCategoryVo> page = productCategoryService.page(new Page<>(1, 500), productCategory);
        List<ProductCategoryVo> records = page.getRecords();
        return BeanCopy.copytList(records, PublicProductCategoryVo.class);
    }
}

