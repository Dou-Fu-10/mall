package org.example.modules.order.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by PanShiFu 2023-07-14 14:34:30
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:30
 * @Description 退货原因表(OrderReturnReason)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReturnReasonVo {
    /**
     * ID
     */
    private Long id;

    /**
     * 退货类型
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态：0->不启用；1->启用
     */
    private Integer status;
    /**
     * 添加时间
     */
    private Date createTime;


}

