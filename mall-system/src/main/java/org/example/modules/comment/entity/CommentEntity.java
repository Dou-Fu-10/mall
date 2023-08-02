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
 * Created by Dou-Fu-10 2023-07-13 21:35:10
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:10
 * @Description 商品评价表(Comment)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_comment")
@Schema(name = "pms_comment", description = "商品评价表(Comment)表实体类")
public class CommentEntity extends CommonEntity<CommentEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 会员昵称
     */
    @Schema(name = "memberNickName", description = "会员昵称")
    private String memberNickName;
    /**
     * 商品名称
     */
    @Schema(name = "productName", description = "商品名称")
    private String productName;
    /**
     * 评价星数：0->5
     */
    @Schema(name = "star", description = "评价星数：0->5")
    private Integer star;
    /**
     * 评价的ip
     */
    @Schema(name = "memberIp", description = "评价的ip")
    private String memberIp;
    /**
     * 显示状态  0->不显示：1->显示
     */
    @Schema(name = "showStatus", description = "显示状态  0->不显示：1->显示")
    private Boolean showStatus;
    /**
     * 购买时的商品属性
     */
    @Schema(name = "productAttribute", description = "购买时的商品属性")
    private String productAttribute;
    /**
     * 收藏次数
     */
    @Schema(name = "collectCount", description = "收藏次数")
    private Integer collectCount;
    /**
     * 浏览次数
     */
    @Schema(name = "readCount", description = "浏览次数")
    private Integer readCount;
    /**
     * 评论
     */
    @Schema(name = "content", description = "评论")
    private String content;
    /**
     * 上传图片地址，以逗号隔开
     */
    @Schema(name = "pics", description = "上传图片地址，以逗号隔开")
    private String pics;
    /**
     * 评论用户头像
     */
    @Schema(name = "memberIcon", description = "评论用户头像")
    private String memberIcon;
    @Schema(name = "replayCount", description = "${column.comment}")
    private Integer replayCount;
    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;


}

