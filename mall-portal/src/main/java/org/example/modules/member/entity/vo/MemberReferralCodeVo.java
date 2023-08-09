package org.example.modules.member.entity.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class MemberReferralCodeVo {
    /**
     * Id
     */
    @Schema(name = "id", description = "Id")
    private Long id;
    /**
     * 会员id
     */
    @Schema(name = "memberId", description = "会员id")
    private Long memberId;

    /**
     * 推荐码
     */
    @Schema(name = "code", description = "推荐码")
    private String code;


}

