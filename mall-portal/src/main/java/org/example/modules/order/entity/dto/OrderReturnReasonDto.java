package org.example.modules.order.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 退货原因表(OrderReturnReason)表实体类
 * Created by Dou-Fu-10 2023-08-11 20:20:01
 *
 * @author Dou-Fu-10
 * @date 2023-08-11 20:20:01
 * @Description 退货原因表(OrderReturnReason)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReturnReasonDto {
    /**
     * ID
     */
    @Schema(name = "id", description = "ID")
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
     * 创建者
     */
    @Schema(name = "createBy", description = "创建者")
    private String createBy;
    /**
     * 更新者
     */
    @Schema(name = "updateBy", description = "更新者")
    private String updateBy;
    /**
     * 添加时间
     */
    @Schema(name = "createTime", description = "添加时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;

}

