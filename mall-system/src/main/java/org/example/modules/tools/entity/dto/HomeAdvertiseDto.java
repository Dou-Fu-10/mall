package org.example.modules.tools.entity.dto;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by Dou-Fu-10 2023-07-13 15:39:31
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:39:31
 * @Description 首页轮播广告表(HomeAdvertise)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeAdvertiseDto {
    private Long id;

    /**
     * 轮播图名称
     */
    private String name;
    /**
     * 轮播位置：0->PC首页轮播；1->app首页轮播
     */
    private Integer type;
    /**
     * 图片路径
     */
    private String pic;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 上下线状态：0->下线；1->上线
     */
    private Integer status;
    /**
     * 点击数
     */
    private Integer clickCount;
    /**
     * 下单数
     */
    private Integer orderCount;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 备注
     */
    private String note;
    /**
     * 排序
     */
    private Integer sort;


}

