package org.example.modules.product.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:57
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:57
 * @Description (ProductOperateLog)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOperateLogVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 旧价格
     */
    private Double priceOld;
    /**
     * 新价格
     */
    private Double priceNew;
    /**
     * 销售旧价格
     */
    private Double salePriceOld;
    /**
     * 销售新价格
     */
    private Double salePriceNew;
    /**
     * 更新价格者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;


}

