package org.example.modules.member.entity.vo;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-13 14:08:44
 *
 * @author PanShiFu
 * @date 2023-07-13 14:08:44
 * @Description 会员等级表(MemberLevel)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLevelVo {
    /**
     * ID
     */
    private Long id;

    /**
     * 会员等级名字
     */
    private String name;
    /**
     * 增长点（需要多少成长点，成长为当级会员）
     */
    private Integer growthPoint;
    /**
     * 是否为默认等级：0->是；1->不是
     */
    private Integer defaultStatus;
    /**
     * 免运费标准（多少钱免运费）
     */
    private Double freeFreightPoint;
    /**
     * 每次评价获取的成长值
     */
    private Integer commentGrowthPoint;
    /**
     * 是否有免邮特权
     */
    private Integer priviledgeFreeFreight;
    /**
     * 是否有签到特权
     */
    private Integer priviledgeSignIn;
    /**
     * 是否有评论获奖励特权
     */
    private Integer priviledgeComment;
    /**
     * 是否有专享活动特权
     */
    private Integer priviledgePromotion;
    /**
     * 是否有会员价格特权
     */
    private Integer priviledgeMemberPrice;
    /**
     * 是否有生日特权
     */
    private Integer priviledgeBirthday;
    /**
     * 注意
     */
    private String note;


}

