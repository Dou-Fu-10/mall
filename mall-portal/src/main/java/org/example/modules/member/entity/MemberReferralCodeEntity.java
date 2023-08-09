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
 * Created by Dou-Fu-10 2023-08-09 12:46:30
 *
 * @author Dou-Fu-10
 * @date 2023-08-09 12:46:30
 * @Description 推荐码(MemberReferralCode)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_member_referral_code")
@Schema(name = "ums_member_referral_code", description = "推荐码(MemberReferralCode)表实体类")
public class MemberReferralCodeEntity extends CommonEntity<MemberReferralCodeEntity> implements Serializable {
    /**
     * Id
     */
    @TableId
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 推荐码
     */
    @Schema(name = "code", description = "推荐码")
    private String code;


}

