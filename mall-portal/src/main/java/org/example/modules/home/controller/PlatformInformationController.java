package org.example.modules.home.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.home.entity.PlatformInformationEntity;
import org.example.modules.home.entity.dto.PlatformInformationDto;
import org.example.modules.home.service.PlatformInformationService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-31 22:28:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 22:28:56
 * @Description 平台信息(PlatformInformation)表控制层
 */
@RestController
@RequestMapping("/platformInformation")
@Tag(name = "PlatformInformationController", description = "")
public class PlatformInformationController {
    /**
     * 服务对象
     */
    @Resource
    private PlatformInformationService platformInformationService;

    /**
     * 分页查询所有数据
     *
     * @param page                   分页对象
     * @param platformInformationDto 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<PlatformInformationEntity> page, PlatformInformationDto platformInformationDto) {
        return ResponseEntity.ok(this.platformInformationService.page(page, platformInformationDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.platformInformationService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param platformInformationDto 实体对象
     * @return 新增结果
     */
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody PlatformInformationDto platformInformationDto) {
        if (this.platformInformationService.save(platformInformationDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param platformInformationDto 实体对象
     * @return 修改结果
     */
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody PlatformInformationDto platformInformationDto) {
        if (this.platformInformationService.updateById(platformInformationDto)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @AnonymousDeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> collect = idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet());
        return ResponseEntity.ok(this.platformInformationService.removeByIds(collect) ? "删除成功" : "删除失败");
    }
}
