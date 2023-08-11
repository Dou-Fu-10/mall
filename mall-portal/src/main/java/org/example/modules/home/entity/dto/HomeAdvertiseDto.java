package org.example.modules.home.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class HomeAdvertiseDto {
    /**
     * ID
     */
    private Long id;

    /**
     * 商品id
     */
    private Long productId;
    /**
     * 轮播图名称
     */
    private String name;
    /**
     * 轮播位置：true=1->PC首页轮播；false=0->app首页轮播
     */
    private Boolean isPc;
    /**
     * 图片路径
     */
    private String pic;

    /**
     * 上下线状态：false=0->下线；true=1->上线
     */
    private Boolean isShow;
    /**
     * 点击数
     */
    private Integer clickCount;
    /**
     * 备注
     */
    private String note;
    /**
     * 排序
     */
    private Integer sort;


}

