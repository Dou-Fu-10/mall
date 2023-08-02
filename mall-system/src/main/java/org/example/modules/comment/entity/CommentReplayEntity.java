package org.example.modules.comment.entity;

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
 * Created by Dou-Fu-10 2023-07-13 21:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:11
 * @Description 产品评价回复表(CommentReplay)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_comment_replay")
@Schema(name = "pms_comment_replay", description = "产品评价回复表(CommentReplay)表实体类")
public class CommentReplayEntity extends CommonEntity<CommentReplayEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 会员昵称
     */
    @Schema(name = "memberNickName", description = "会员昵称")
    private String memberNickName;
    /**
     * 会员头像
     */
    @Schema(name = "memberIcon", description = "会员头像")
    private String memberIcon;
    /**
     * 评论内容
     */
    @Schema(name = "content", description = "评论内容")
    private String content;
    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;
    /**
     * 评论人员类型；0->管理员；1->会员
     */
    @Schema(name = "isMember", description = "评论人员类型；0->管理员；1->会员")
    private Boolean isMember;


}

