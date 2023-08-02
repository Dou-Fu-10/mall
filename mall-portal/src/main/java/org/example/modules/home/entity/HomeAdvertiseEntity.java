package org.example.modules.home.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-31 16:16:51
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 16:16:51
 * @Description 首页轮播广告表(HomeAdvertise)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sms_home_advertise")
@Schema(name = "sms_home_advertise", description = "首页轮播广告表(HomeAdvertise)表实体类")
public class HomeAdvertiseEntity extends CommonEntity<HomeAdvertiseEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 商品id
     */
    @Schema(name = "productId", description = "商品id")
    private Long productId;
    /**
     * 轮播图名称
     */
    @Schema(name = "name", description = "轮播图名称")
    private String name;
    /**
     * 轮播位置：0->PC首页轮播；1->app首页轮播
     */
    @Schema(name = "ispc", description = "轮播位置：0->PC首页轮播；1->app首页轮播")
    private Integer ispc;
    /**
     * 图片路径
     */
    @Schema(name = "pic", description = "图片路径")
    private String pic;
    /**
     * 开始时间
     */
    @Schema(name = "startTime", description = "开始时间")
    private Date startTime;
    /**
     * 结束时间
     */
    @Schema(name = "endTime", description = "结束时间")
    private Date endTime;
    /**
     * 上下线状态：0->下线；1->上线
     */
    @Schema(name = "isShow", description = "上下线状态：0->下线；1->上线")
    private Integer isShow;
    /**
     * 点击数
     */
    @Schema(name = "clickCount", description = "点击数")
    private Integer clickCount;
    /**
     * 链接地址
     */
    @Schema(name = "url", description = "链接地址")
    private String url;
    /**
     * 备注
     */
    @Schema(name = "note", description = "备注")
    private String note;
    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @JsonIgnore
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;

}
