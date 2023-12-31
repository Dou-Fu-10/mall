package org.example.modules.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:18
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:18
 * @Description 商品价格变动记录(ProductOperateLog)表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_operate_log")
@Schema(name = "pms_product_operate_log", description = "商品价格变动记录(ProductOperateLog)表实体类")
public class ProductOperateLogEntity extends CommonEntity<ProductOperateLogEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 旧价格
     */
    @Schema(name = "priceOld", description = "旧价格")
    private BigDecimal priceOld;
    /**
     * 新价格
     */
    @Schema(name = "priceNew", description = "新价格")
    private BigDecimal priceNew;
    /**
     * 销售旧价格
     */
    @Schema(name = "salePriceOld", description = "销售旧价格")
    private BigDecimal salePriceOld;
    /**
     * 销售新价格
     */
    @Schema(name = "salePriceNew", description = "销售新价格")
    private BigDecimal salePriceNew;
    /**
     * 更新价格者
     */
    @Schema(name = "createBy", description = "更新价格者")
    private String createBy;
    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;


}

