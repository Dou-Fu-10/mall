package org.example.modules.order.entity.dto;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-13 14:31:57
 *
 * @author PanShiFu
 * @date 2023-07-13 14:31:57
 * @Description 订单操作历史记录(OrderOperateHistory)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOperateHistoryDto {
    /**
     * ID
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 操作人：用户；系统；后台管理员
     */
    private String operateMan;
    /**
     * 操作时间
     */
    private Date createTime;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private Integer orderStatus;
    /**
     * 备注
     */
    private String note;


}

