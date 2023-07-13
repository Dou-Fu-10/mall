package org.example.modules.member.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-07-13 15:34:48
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:34:48
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
     * 是否有免邮特权
     */
    @Schema(name = "priviledgeFreeFreight", description = "是否有免邮特权")
    private Integer priviledgeFreeFreight;
    /**
     * 是否有会员价格特权
     */
    @Schema(name = "priviledgeMemberPrice", description = "是否有会员价格特权")
    private Integer priviledgeMemberPrice;
    /**
     * 注意
     */
    @Schema(name = "note", description = "注意")
    private String note;


}

