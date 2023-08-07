package org.example.modules.tools.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.tools.entity.CompanyAddressEntity;
import org.example.modules.tools.entity.dto.CompanyAddressDto;
import org.example.modules.tools.service.CompanyAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:01
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:01
 * @Description 公司收发货地址表(CompanyAddress)表控制层
 */
@RestController
@RequestMapping("/api/companyAddress")
@Tag(name = "CompanyAddressController", description = "公司收发货地址表(CompanyAddress)表控制层")
public class CompanyAddressController {
    /**
     * 服务对象
     */
    @Resource
    private CompanyAddressService companyAddressService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param companyAddress 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据", description = "companyAddress::select")
    @PreAuthorize("@hasPermission.check('companyAddress::select')")
    public ResponseEntity<Object> select(Page<CompanyAddressEntity> page, CompanyAddressEntity companyAddress) {
        return ResponseEntity.ok(this.companyAddressService.page(page, new QueryWrapper<>(companyAddress)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据", description = "companyAddress::selectOne")
    @PreAuthorize("@hasPermission.check('companyAddress::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.companyAddressService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param companyAddress 实体对象
     * @return 新增结果
     */
    @PostMapping
    @Operation(summary = "新增数据", description = "companyAddress::insert")
    @PreAuthorize("@hasPermission.check('companyAddress::insert')")
    public ResponseEntity<Object> insert(@RequestBody CompanyAddressDto companyAddress) {
        if (this.companyAddressService.save(companyAddress)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param companyAddress 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据", description = "companyAddress::update")
    @PreAuthorize("@hasPermission.check('companyAddress::update')")
    public ResponseEntity<Object> update(@RequestBody CompanyAddressDto companyAddress) {
        if (this.companyAddressService.updateById(companyAddress)) {
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
    @DeleteMapping
    @Operation(summary = "删除数据", description = "companyAddress::remove")
    @PreAuthorize("@hasPermission.check('companyAddress::remove')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (this.companyAddressService.removeByIds(ids)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

