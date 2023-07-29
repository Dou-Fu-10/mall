package org.example.modules.admin.system.entity.dto;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
public class PrizePoolDto {
    /**
     * Id
     */
    private Long id;

    /**
     * 商品奖金池占比
     */
    private Integer productBonusesPercentage;
    /**
     * 会员奖金池占比
     */
    private Integer memberBonusesPercentage;
    /**
     * 会员费用
     */
    private Double memberFees;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;


}

