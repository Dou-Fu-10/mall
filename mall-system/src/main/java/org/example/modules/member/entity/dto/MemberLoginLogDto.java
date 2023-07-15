package org.example.modules.member.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:17
 * @Description 会员登录记录(MemberLoginLog)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginLogDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 用户创建时间
     */
    private Date createTime;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 城市
     */
    private String city;
    /**
     * 登录类型：0->PC；1->android;2->ios;3->小程序
     */
    private Integer loginType;
    /**
     * 省
     */
    private String province;


}

