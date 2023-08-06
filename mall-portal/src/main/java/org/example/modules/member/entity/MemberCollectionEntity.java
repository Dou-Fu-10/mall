package org.example.modules.member.entity;


import com.baomidou.mybatisplus.annotation.TableField;
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
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员收藏(MemberCollection)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_member_collection")
@Schema(name = "ums_member_collection", description = "会员收藏(MemberCollection)表实体类")
public class MemberCollectionEntity extends CommonEntity<MemberCollectionEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 创建人
     * 创建
     */
    @TableField(exist = false)
    private String createBy;
    /**
     * 更新人
     * 创建、更新
     */
    @TableField(exist = false)
    private String updateBy;
    /**
     * 创建时间
     * 创建
     */
    @TableField(exist = false)
    private Date createTime;
    /**
     * 更新时间
     * 创建、更新
     */
    @TableField(exist = false)
    private Date updateTime;

}

