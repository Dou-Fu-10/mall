package org.example.modules.member.entity;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

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
@TableName("ums_member_level")
@Schema(name = "ums_member_level", description = "会员等级表(MemberLevel)表实体类")
public class MemberLevelEntity extends CommonEntity<MemberLevelEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 会员等级名字
     */
    @Schema(name = "name", description = "会员等级名字")
    private String name;
    /**
     * 增长点（需要多少成长点，成长为当级会员）
     */
    @Schema(name = "growthPoint", description = "增长点（需要多少成长点，成长为当级会员）")
    private Integer growthPoint;
    /**
     * 是否为默认等级：0->是；1->不是
     */
    @Schema(name = "defaultStatus", description = "是否为默认等级：0->是；1->不是")
    private Integer defaultStatus;
    /**
     * 免运费标准（多少钱免运费）
     */
    @Schema(name = "freeFreightPoint", description = "免运费标准（多少钱免运费）")
    private Double freeFreightPoint;
    /**
     * 每次评价获取的成长值
     */
    @Schema(name = "commentGrowthPoint", description = "每次评价获取的成长值")
    private Integer commentGrowthPoint;
    /**
     * 是否有免邮特权
     */
    @Schema(name = "priviledgeFreeFreight", description = "是否有免邮特权")
    private Integer priviledgeFreeFreight;
    /**
     * 是否有签到特权
     */
    @Schema(name = "priviledgeSignIn", description = "是否有签到特权")
    private Integer priviledgeSignIn;
    /**
     * 是否有评论获奖励特权
     */
    @Schema(name = "priviledgeComment", description = "是否有评论获奖励特权")
    private Integer priviledgeComment;
    /**
     * 是否有专享活动特权
     */
    @Schema(name = "priviledgePromotion", description = "是否有专享活动特权")
    private Integer priviledgePromotion;
    /**
     * 是否有会员价格特权
     */
    @Schema(name = "priviledgeMemberPrice", description = "是否有会员价格特权")
    private Integer priviledgeMemberPrice;
    /**
     * 是否有生日特权
     */
    @Schema(name = "priviledgeBirthday", description = "是否有生日特权")
    private Integer priviledgeBirthday;
    /**
     * 注意
     */
    @Schema(name = "note", description = "注意")
    private String note;


}

