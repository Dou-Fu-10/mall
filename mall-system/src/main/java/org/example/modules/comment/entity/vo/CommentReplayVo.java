package org.example.modules.comment.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by PanShiFu 2023-07-13 21:35:11
 *
 * @author PanShiFu
 * @date 2023-07-13 21:35:11
 * @Description 产品评价回复表(CommentReplay)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentReplayVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 会员昵称
     */
    private String memberNickName;
    /**
     * 会员头像
     */
    private String memberIcon;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 评论人员类型；0->管理员；1->会员
     */
    private Integer isMember;


}

