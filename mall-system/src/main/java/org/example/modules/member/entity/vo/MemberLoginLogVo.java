package org.example.modules.member.entity.vo;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-13 14:08:45
 *
 * @author PanShiFu
 * @date 2023-07-13 14:08:45
 * @Description 会员登录记录(MemberLoginLog)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginLogVo {
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

