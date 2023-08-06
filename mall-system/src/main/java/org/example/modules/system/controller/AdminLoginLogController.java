package org.example.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.system.entity.AdminLoginLogEntity;
import org.example.modules.system.service.AdminLoginLogService;
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
 * Created by Dou-Fu-10 2023-07-09 17:33:01
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 17:33:01
 * @Description 后台用户登录日志表(AdminLoginLog)表控制层
 */
//@RestController
//@RequestMapping("/api/adminLoginLog")
//@Tag(name = "AdminLoginLogController", description = "后台用户登录日志表(AdminLoginLog)表控制层")
public class AdminLoginLogController {
    /**
     * 服务对象
     */
//    @Resource
//    private AdminLoginLogService adminLoginLogService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param umsAdminLoginLog 查询实体
     * @return 所有数据
     */
//    @AnonymousGetMapping
//    public ResponseEntity<Object> selectAll(Page<AdminLoginLogEntity> page, AdminLoginLogEntity umsAdminLoginLog) {
//        return new ResponseEntity<>(this.adminLoginLogService.page(page, new QueryWrapper<>(umsAdminLoginLog)), HttpStatus.OK);
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @AnonymousGetMapping("{id}")
//    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
//        return new ResponseEntity<>(this.adminLoginLogService.getById(id), HttpStatus.OK);
//    }

    /**
     * 新增数据
     *
     * @param umsAdminLoginLog 实体对象
     * @return 新增结果
     */
//    @AnonymousPostMapping
//    public ResponseEntity<Object> insert(@RequestBody AdminLoginLogEntity umsAdminLoginLog) {
//        return new ResponseEntity<>(this.adminLoginLogService.save(umsAdminLoginLog), HttpStatus.OK);
//    }

    /**
     * 修改数据
     *
     * @param umsAdminLoginLog 实体对象
     * @return 修改结果
     */
//    @AnonymousPutMapping
//    public ResponseEntity<Object> update(@RequestBody AdminLoginLogEntity umsAdminLoginLog) {
//        return new ResponseEntity<>(this.adminLoginLogService.updateById(umsAdminLoginLog), HttpStatus.OK);
//    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
//    @AnonymousDeleteMapping
//    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
//        if (CollectionUtils.isEmpty(idList)) {
//            throw new BaseRequestException("请正确的填写id");
//        }
//        return new ResponseEntity<>(this.adminLoginLogService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
//    }
}

