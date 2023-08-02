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
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:18
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:18
 * @Description 商品审核记录(ProductVertifyRecord)表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_vertify_record")
@Schema(name = "pms_product_vertify_record", description = "商品审核记录(ProductVertifyRecord)表实体类")
public class ProductVertifyRecordEntity extends CommonEntity<ProductVertifyRecordEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 产品id
     */
    private Long productId;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;
    /**
     * 审核人
     */
    @Schema(name = "vertifyMan", description = "审核人")
    private String vertifyMan;
    /**
     * 状态
     */
    @Schema(name = "status", description = "状态")
    private Integer status;
    /**
     * 反馈详情
     */
    @Schema(name = "detail", description = "反馈详情")
    private String detail;


}

