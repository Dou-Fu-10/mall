package org.example.modules.tools.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.ValidationDto;

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
public class HomeAdvertiseDto {
    /**
     * ID
     */
    @Schema(name = "id", description = "ID")
    private Long id;

    /**
     * 商品id
     */
    @Null(groups = {ValidationDto.SelectPage.class})
    @Schema(name = "productId", description = "商品id")
    private Long productId;
    /**
     * 轮播图名称
     */
    @Schema(name = "name", description = "轮播图名称")
    private String name;
    /**
     * 轮播位置：true->PC首页轮播；false->app首页轮播
     */
    @Schema(name = "isPc", description = "轮播位置：true->PC首页轮播；false->app首页轮播")
    private Boolean isPc;
    /**
     * 图片路径
     */
    @Null(groups = {ValidationDto.SelectPage.class})
    @Schema(name = "pic", description = "图片路径")
    @NotEmpty(groups = {ValidationDto.Insert.class, ValidationDto.Update.class})
    private String pic;
    /**
     * 上下线状态：false->下线；true->上线
     */
    @Schema(name = "isShow", description = "上下线状态：false->下线；true->上线")
    private Boolean isShow;
    /**
     * 点击数
     */
    @Null(groups = {ValidationDto.SelectPage.class})
    @Schema(name = "clickCount", description = "点击数")
    private Integer clickCount;
    /**
     * 备注
     */
    @Null(groups = {ValidationDto.SelectPage.class})
    @Schema(name = "note", description = "备注")
    private String note;
    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;
}

