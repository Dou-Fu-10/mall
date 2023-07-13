package org.example.modules.comment.entity.vo;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by Dou-Fu-10 2023-07-13 15:19:46
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:19:46
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
     * 评论人员类型；0->会员；1->管理员
     */
    private Integer type;


}

