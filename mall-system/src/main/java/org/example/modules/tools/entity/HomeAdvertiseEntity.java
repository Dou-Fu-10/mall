package org.example.modules.tools.entity;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

/**
 * Created by PanShiFu 2023-07-13 15:39:31
 *
 * @author PanShiFu
 * @date 2023-07-13 15:39:31
 * @Description 首页轮播广告表(HomeAdvertise)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sms_home_advertise")
@Schema(name = "sms_home_advertise", description = "首页轮播广告表(HomeAdvertise)表实体类")
public class HomeAdvertiseEntity extends CommonEntity<HomeAdvertiseEntity> implements Serializable {
    @TableId
    private Long id;

    /**
     * 轮播图名称
     */
    @Schema(name = "name", description = "轮播图名称")
    private String name;
    /**
     * 轮播位置：0->PC首页轮播；1->app首页轮播
     */
    @Schema(name = "type", description = "轮播位置：0->PC首页轮播；1->app首页轮播")
    private Integer type;
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
    @Schema(name = "status", description = "上下线状态：0->下线；1->上线")
    private Integer status;
    /**
     * 点击数
     */
    @Schema(name = "clickCount", description = "点击数")
    private Integer clickCount;
    /**
     * 下单数
     */
    @Schema(name = "orderCount", description = "下单数")
    private Integer orderCount;
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


}

