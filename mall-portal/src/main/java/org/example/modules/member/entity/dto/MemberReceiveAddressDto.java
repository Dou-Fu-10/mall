package org.example.modules.member.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.validation.PasswordValid;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员收货地址表(MemberReceiveAddress)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberReceiveAddressDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 会员id
     */
    @JsonIgnore
    private Long memberId;

    /**
     * 收货人名称
     */
    @NotNull
    private String name;
    /**
     * 手机号码
     */
    @PasswordValid
    private String phone;
    /**
     * 是否为默认
     */
    private Boolean defaultStatus;
    /**
     * 邮政编码
     */
    @NotNull
    private String postCode;
    /**
     * 省份/直辖市
     */
    @NotNull
    private String province;
    /**
     * 城市
     */
    @NotNull
    private String city;
    /**
     * 区
     */
    @NotNull
    private String region;
    /**
     * 详细地址(街道)
     */
    @NotNull
    private String detailAddress;


}

