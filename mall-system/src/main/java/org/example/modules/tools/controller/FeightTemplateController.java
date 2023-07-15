package org.example.modules.tools.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.tools.entity.FeightTemplateEntity;
import org.example.modules.tools.entity.dto.FeightTemplateDto;
import org.example.modules.tools.service.FeightTemplateService;
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
 * Created by Dou-Fu-10 2023-07-14 14:36:26
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:26
 * @Description 运费模版(FeightTemplate)表控制层
 */
@RestController
@RequestMapping("/api/feightTemplate")
@Tag(name = "FeightTemplateController", description = "运费模版(FeightTemplate)表控制层")
public class FeightTemplateController {
    /**
     * 服务对象
     */
    @Resource
    private FeightTemplateService feightTemplateService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param feightTemplate 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<FeightTemplateEntity> page, FeightTemplateEntity feightTemplate) {
        return ResponseEntity.ok(this.feightTemplateService.page(page, new QueryWrapper<>(feightTemplate)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.feightTemplateService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param feightTemplate 实体对象
     * @return 新增结果
     */
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody FeightTemplateDto feightTemplate) {
        if (this.feightTemplateService.save(feightTemplate)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param feightTemplate 实体对象
     * @return 修改结果
     */
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody FeightTemplateDto feightTemplate) {
        if (this.feightTemplateService.updateById(feightTemplate)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @AnonymousDeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return ResponseEntity.ok(this.feightTemplateService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败");
    }
}

