package org.example.modules.member.entity;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

/**
 * 奖金池每天收益(MemberBonus)表实体类
 * Created by Dou-Fu-10 2023-08-13 15:14:54
 *
 * @author Dou-Fu-10
 * @date 2023-08-13 15:14:54
 * @Description 奖金池每天收益(MemberBonus)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_member_bonus")
@Schema(name = "ums_member_bonus", description = "奖金池每天收益(MemberBonus)表实体类")
public class MemberBonusEntity extends CommonEntity<MemberBonusEntity> implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 奖金
     */
    @Schema(name = "memberBonus", description = "奖金")
    private BigDecimal memberBonus;
    /**
     * 创建日期
     */
    @Schema(name = "createTime", description = "创建日期")
    private Date createTime;
    /**
     * 创建人
     * 创建
     */
    @TableField(exist = false)
    private String createBy;
    /**
     * 更新人
     * 创建、更新
     */
    @TableField(exist = false)
    private String updateBy;
    /**
     * 更新时间
     * 创建、更新
     */
    @TableField(exist = false)
    private Date updateTime;
}

