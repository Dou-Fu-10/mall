package org.example.modules.admin.system.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.admin.system.service.PrizePoolService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.admin.system.entity.dto.PrizePoolDto;
import org.example.modules.admin.system.entity.PrizePoolEntity;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-29 15:47:03
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 15:47:03
 * @Description 奖金池(PrizePool)表控制层
 */
@RestController
@RequestMapping("/api/prizePool")
@Tag(name = "PrizePoolController", description = "")
public class PrizePoolController {
    /**
     * 服务对象
     */
    @Resource
    private PrizePoolService prizePoolService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param prizePool 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<PrizePoolEntity> page, PrizePoolDto prizePool) {
        return ResponseEntity.ok(this.prizePoolService.page(page, prizePool));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.prizePoolService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param prizePool 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody PrizePoolDto prizePool) {
        if (this.prizePoolService.save(prizePool)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param prizePool 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody PrizePoolDto prizePool) {
        if (this.prizePoolService.updateById(prizePool)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("修改失败");
    }
}

