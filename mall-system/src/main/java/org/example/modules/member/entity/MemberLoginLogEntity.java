package org.example.modules.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
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
@TableName("ums_member_login_log")
@Schema(name = "ums_member_login_log", description = "会员登录记录(MemberLoginLog)表实体类")
public class MemberLoginLogEntity extends CommonEntity<MemberLoginLogEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 用户创建时间
     */
    @Schema(name = "createTime", description = "用户创建时间")
    private Date createTime;
    /**
     * ip地址
     */
    @Schema(name = "ip", description = "ip地址")
    private String ip;
    /**
     * 城市
     */
    @Schema(name = "city", description = "城市")
    private String city;
    /**
     * 登录类型：0->PC；1->android;2->ios;3->小程序
     */
    @Schema(name = "loginType", description = "登录类型：0->PC；1->android;2->ios;3->小程序")
    private Integer loginType;
    /**
     * 省
     */
    @Schema(name = "province", description = "省")
    private String province;


}
