package org.example.modules.product.entity;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:56
 * @Description (ProductOperateLog)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_operate_log")
@Schema(name = "pms_product_operate_log", description = "(ProductOperateLog)表实体类")
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
    private Double priceOld;
    /**
     * 新价格
     */
    @Schema(name = "priceNew", description = "新价格")
    private Double priceNew;
    /**
     * 销售旧价格
     */
    @Schema(name = "salePriceOld", description = "销售旧价格")
    private Double salePriceOld;
    /**
     * 销售新价格
     */
    @Schema(name = "salePriceNew", description = "销售新价格")
    private Double salePriceNew;
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

