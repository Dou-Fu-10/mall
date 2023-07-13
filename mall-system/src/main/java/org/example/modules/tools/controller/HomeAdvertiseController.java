package org.example.modules.tools.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.tools.entity.HomeAdvertiseEntity;
import org.example.modules.tools.service.HomeAdvertiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-13 15:39:31
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:39:31
 * @Description 首页轮播广告表(HomeAdvertise)表控制层
 */
@RestController
@RequestMapping("/api/homeAdvertise")
@Tag(name = "HomeAdvertiseController", description = "首页轮播广告表(HomeAdvertise)表控制层")
public class HomeAdvertiseController {
    /**
     * 服务对象
     */
    @Resource
    private HomeAdvertiseService homeAdvertiseService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param homeAdvertise 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<HomeAdvertiseEntity> page, HomeAdvertiseEntity homeAdvertise) {
        return new ResponseEntity<>(this.homeAdvertiseService.page(page, new QueryWrapper<>(homeAdvertise)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.homeAdvertiseService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param homeAdvertise 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody HomeAdvertiseEntity homeAdvertise) {
        return new ResponseEntity<>(this.homeAdvertiseService.save(homeAdvertise), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param homeAdvertise 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody HomeAdvertiseEntity homeAdvertise) {
        return new ResponseEntity<>(this.homeAdvertiseService.updateById(homeAdvertise), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.homeAdvertiseService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

