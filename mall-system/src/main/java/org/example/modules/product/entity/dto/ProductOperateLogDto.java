package org.example.modules.product.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:18
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:18
 * @Description 商品价格变动记录(ProductOperateLog)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOperateLogDto {
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
    private BigDecimal priceOld;
    /**
     * 新价格
     */
    private BigDecimal priceNew;
    /**
     * 销售旧价格
     */
    private BigDecimal salePriceOld;
    /**
     * 销售新价格
     */
    private BigDecimal salePriceNew;
    /**
     * 更新价格者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;


}

