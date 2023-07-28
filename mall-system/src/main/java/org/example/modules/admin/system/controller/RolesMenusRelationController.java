package org.example.modules.admin.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.admin.system.entity.RolesMenusRelationEntity;
import org.example.modules.admin.system.service.RolesMenusRelationService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:14
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:14
 * @Description 角色菜单关联(RolesMenus)表控制层
 */
@RestController
@RequestMapping("/api/rolesMenus")
@Tag(name = "RolesMenusRelationController", description = "角色菜单关联(RolesMenus)表控制层")
public class RolesMenusRelationController {
    /**
     * 服务对象
     */
    @Resource
    private RolesMenusRelationService rolesMenusRelationService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param rolesMenus 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<RolesMenusRelationEntity> page, RolesMenusRelationEntity rolesMenus) {
        return new ResponseEntity<>(this.rolesMenusRelationService.page(page, new QueryWrapper<>(rolesMenus)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.rolesMenusRelationService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param rolesMenus 实体对象
     * @return 新增结果
     */
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody RolesMenusRelationEntity rolesMenus) {
        return new ResponseEntity<>(this.rolesMenusRelationService.save(rolesMenus), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param rolesMenus 实体对象
     * @return 修改结果
     */
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody RolesMenusRelationEntity rolesMenus) {
        return new ResponseEntity<>(this.rolesMenusRelationService.updateById(rolesMenus), HttpStatus.OK);
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
        return new ResponseEntity<>(this.rolesMenusRelationService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

