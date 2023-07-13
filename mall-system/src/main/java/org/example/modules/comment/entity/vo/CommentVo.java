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
 * @Description 商品评价表(Comment)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 会员昵称
     */
    private String memberNickName;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 评价星数：0->5
     */
    private Integer star;
    /**
     * 评价的ip
     */
    private String memberIp;
    /**
     * 显示状态  0->不显示：1->显示
     */
    private Integer showStatus;
    /**
     * 购买时的商品属性
     */
    private String productAttribute;
    /**
     * 收藏次数
     */
    private Integer collectCount;
    /**
     * 浏览次数
     */
    private Integer readCount;
    /**
     * 评论
     */
    private String content;
    /**
     * 上传图片地址，以逗号隔开
     */
    private String pics;
    /**
     * 评论用户头像
     */
    private String memberIcon;
    private Integer replayCount;
    /**
     * 创建时间
     */
    private Date createTime;


}

