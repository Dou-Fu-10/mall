package org.example.modules.admin.system.entity;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

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
     * 会员费用
     */
    @Schema(name = "memberFees", description = "会员费用")
    private Double memberFees;
    /**
     * 创建日期
     */
    @Schema(name = "createTime", description = "创建日期")
    private Date createTime;
    /**
     * 修改时间
     */
    @Schema(name = "updateTime", description = "修改时间")
    private Date updateTime;


}

