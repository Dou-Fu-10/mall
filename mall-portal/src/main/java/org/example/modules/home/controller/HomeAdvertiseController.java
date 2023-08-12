package org.example.modules.home.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.home.entity.HomeAdvertiseEntity;
import org.example.modules.home.entity.dto.HomeAdvertiseDto;
import org.example.modules.home.entity.vo.HomeAdvertiseVo;
import org.example.modules.home.service.HomeAdvertiseService;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 首页轮播广告 HomeAdvertiseController
 * Created by Dou-Fu-10 2023-07-31 16:16:51
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 16:16:51
 * @Description 首页轮播广告表(HomeAdvertise)表控制层
 */
@RestController
@RequestMapping("/homeAdvertise")
@Tag(name = "HomeAdvertiseController", description = "页轮播广告表(HomeAdvertise)表控制层")
public class HomeAdvertiseController {
    /**
     * 服务对象
     */
    @Resource
    private HomeAdvertiseService homeAdvertiseService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param isPc 是否是pc
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @AnonymousGetMapping
    public ResponseEntity<Object> select(Page<HomeAdvertiseEntity> page, Boolean isPc) {
        HomeAdvertiseDto homeAdvertiseDto = new HomeAdvertiseDto();
        homeAdvertiseDto.setIsShow(false);
        if (Objects.isNull(isPc)) {
            homeAdvertiseDto.setIsPc(false);
        } else {
            homeAdvertiseDto.setIsPc(isPc);
        }

        return ResponseEntity.ok(this.homeAdvertiseService.page(page, homeAdvertiseDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据")
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Long id) {
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数有误");
        }
        HomeAdvertiseEntity homeAdvertiseEntity = new HomeAdvertiseEntity();
        homeAdvertiseEntity.setIsShow(false);
        homeAdvertiseEntity.setId(id);
        HomeAdvertiseEntity homeAdvertise = this.homeAdvertiseService.getOne(new QueryWrapper<>(homeAdvertiseEntity));
        return ResponseEntity.ok(BeanCopy.convert(homeAdvertise, HomeAdvertiseVo.class));
    }
}

