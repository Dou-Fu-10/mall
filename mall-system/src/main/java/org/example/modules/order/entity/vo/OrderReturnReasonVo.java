package org.example.modules.order.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-13 15:31:36
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:31:36
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

