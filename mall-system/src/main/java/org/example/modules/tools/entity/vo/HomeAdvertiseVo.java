package org.example.modules.tools.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-08-10 22:21:00
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 22:21:00
 * @Description 首页轮播广告表(HomeAdvertise)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeAdvertiseVo {
    /**
     * ID
     */
    @Schema(name = "id", description = "ID")
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
     * 轮播位置：true->PC首页轮播；false=0->app首页轮播
     */
    @Schema(name = "isPc", description = "轮播位置：true->PC首页轮播；false=0->app首页轮播")
    private Boolean isPc;
    /**
     * 图片路径
     */
    @Schema(name = "pic", description = "图片路径")
    private String pic;

    /**
     * 上下线状态：false=0->下线；true->上线
     */
    @Schema(name = "isShow", description = "上下线状态：false=0->下线；true->上线")
    private Boolean isShow;
    /**
     * 点击数
     */
    @Schema(name = "clickCount", description = "点击数")
    private Integer clickCount;
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
     * 创建者
     */
    @JsonIgnore
    @Schema(name = "createBy", description = "创建者")
    private String createBy;
    /**
     * 更新者
     */
    @JsonIgnore
    @Schema(name = "updateBy", description = "更新者")
    private String updateBy;
    /**
     * 创建日期
     */
    @JsonIgnore
    @Schema(name = "createTime", description = "创建日期")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonIgnore
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @JsonIgnore
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;

}

