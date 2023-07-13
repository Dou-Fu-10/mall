package org.example.modules.tools.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.tools.entity.FeightTemplateEntity;
import org.example.modules.tools.service.FeightTemplateService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-13 15:33:38
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:33:38
 * @Description 运费模版(FeightTemplate)表控制层
 */
@RestController
@RequestMapping("/api/feightTemplate")
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
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<FeightTemplateEntity> page, FeightTemplateEntity feightTemplate) {
        return new ResponseEntity<>(this.feightTemplateService.page(page, new QueryWrapper<>(feightTemplate)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.feightTemplateService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param feightTemplate 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody FeightTemplateEntity feightTemplate) {
        return new ResponseEntity<>(this.feightTemplateService.save(feightTemplate), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param feightTemplate 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody FeightTemplateEntity feightTemplate) {
        return new ResponseEntity<>(this.feightTemplateService.updateById(feightTemplate), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.feightTemplateService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

