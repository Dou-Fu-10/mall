package org.example.modules.member.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

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
public class MemberBonusVo {
    /**
     * id
     */
    @JsonIgnore
    @Schema(name = "id", description = "id")
    private Long id;

    /**
     * 奖金
     */
    @Schema(name = "memberBonus", description = "奖金")
    private BigDecimal memberBonus;
    /**
     * 创建日期
     */
    @JsonIgnore
    @Schema(name = "createTime", description = "创建日期")
    private Date createTime;

}

