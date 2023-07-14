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
 * Created by PanShiFu 2023-07-14 14:34:29
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:29
 * @Description 订单操作历史记录(OrderOperateHistory)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("oms_order_operate_history")
@Schema(name = "oms_order_operate_history", description = "订单操作历史记录(OrderOperateHistory)表实体类")
public class OrderOperateHistoryEntity extends CommonEntity<OrderOperateHistoryEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 操作人：用户；系统；后台管理员
     */
    @Schema(name = "operateMan", description = "操作人：用户；系统；后台管理员")
    private String operateMan;
    /**
     * 操作时间
     */
    @Schema(name = "createTime", description = "操作时间")
    private Date createTime;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    @Schema(name = "orderStatus", description = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer orderStatus;
    /**
     * 备注
     */
    @Schema(name = "note", description = "备注")
    private String note;


}

