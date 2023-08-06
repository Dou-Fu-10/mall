package org.example.modules.finance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-29 15:47:03
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 15:47:03
 * @Description 奖金池(PrizePool)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_prize_pool")
@Schema(name = "ums_prize_pool", description = "奖金池(PrizePool)表实体类")
public class PrizePoolEntity extends CommonEntity<PrizePoolEntity> implements Serializable {
    /**
     * Id
     */
    @TableId
    private Long id;

    /**
     * 商品奖金池占比
     */
    @Schema(name = "productBonusesPercentage", description = "商品奖金池占比")
    private Integer productBonusesPercentage;
    /**
     * 会员奖金池占比
     */
    @Schema(name = "memberBonusesPercentage", description = "会员奖金池占比")
    private Integer memberBonusesPercentage;
    /**
     * 推荐会员提成比例
     */
    @Schema(name = "recommendedCommissionPercentage", description = "推荐会员提成比例")
    private Integer recommendedCommissionPercentage;
    /**
     * 会员费用
     */
    @Schema(name = "memberFees", description = "会员费用")
    private BigDecimal memberFees;
    /**
     * 创建日期
     */
    @Schema(name = "createTime", description = "创建日期")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(exist = false)
    private Date updateTime;

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
}

