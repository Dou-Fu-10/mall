package org.example.modules.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 退货原因表(OrderReturnReason)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("oms_order_return_reason")
@Schema(name = "oms_order_return_reason", description = "退货原因表(OrderReturnReason)表实体类")
public class OrderReturnReasonEntity extends CommonEntity<OrderReturnReasonEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 退货类型
     */
    @Schema(name = "name", description = "退货类型")
    private String name;
    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;
    /**
     * 状态：0->不启用；1->启用
     */
    @Schema(name = "status", description = "状态：0->不启用；1->启用")
    private Boolean status;
    /**
     * 添加时间
     */
    @Schema(name = "createTime", description = "添加时间")
    private Date createTime;


}

