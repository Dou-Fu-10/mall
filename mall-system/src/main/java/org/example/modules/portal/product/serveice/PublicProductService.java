package org.example.modules.portal.product.serveice;

import org.example.modules.portal.product.entity.vo.PublicProductDetail;

import java.io.Serializable;


/**
 * Created by Dou-Fu-10 2023-07-27 13:05:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-27 13:05:47
 * @Description 商品信息(Product)表服务接口
 */
public interface PublicProductService {
    PublicProductDetail detail(Serializable id);
}
