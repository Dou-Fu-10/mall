package org.example.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.modules.system.entity.AdminLoginLogEntity;
import org.example.modules.system.service.AdminLoginLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by PanShiFu 2023-07-09 17:33:01
 *
 * @author PanShiFu
 * @date 2023-07-09 17:33:01
 * @Description 后台用户登录日志表(AdminLoginLog)表控制层
 */
@RestController
@RequestMapping("/api/adminLoginLog")
public class AdminLoginLogController {
    /**
     * 服务对象
     */
    @Resource
    private AdminLoginLogService adminLoginLogService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param umsAdminLoginLog 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<AdminLoginLogEntity> page, AdminLoginLogEntity umsAdminLoginLog) {
        return new ResponseEntity<>(this.adminLoginLogService.page(page, new QueryWrapper<>(umsAdminLoginLog)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.adminLoginLogService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param umsAdminLoginLog 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody AdminLoginLogEntity umsAdminLoginLog) {
        return new ResponseEntity<>(this.adminLoginLogService.save(umsAdminLoginLog), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param umsAdminLoginLog 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody AdminLoginLogEntity umsAdminLoginLog) {
        return new ResponseEntity<>(this.adminLoginLogService.updateById(umsAdminLoginLog), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.adminLoginLogService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

