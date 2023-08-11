package org.example.modules.member.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long memberId;

    /**
     * 收货人名称
     */
    private String name;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 是否为默认
     */
    private Integer defaultStatus;
    /**
     * 邮政编码
     */
    private String postCode;
    /**
     * 省份/直辖市
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String region;
    /**
     * 详细地址(街道)
     */
    private String detailAddress;


}

