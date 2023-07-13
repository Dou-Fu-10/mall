package org.example.modules.tools.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.tools.entity.CompanyAddressEntity;
import org.example.modules.tools.service.CompanyAddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-13 15:33:00
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:33:00
 * @Description 公司收发货地址表(CompanyAddress)表控制层
 */
@RestController
@RequestMapping("/api/companyAddress")
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
    public ResponseEntity<Object> selectAll(Page<CompanyAddressEntity> page, CompanyAddressEntity companyAddress) {
        return new ResponseEntity<>(this.companyAddressService.page(page, new QueryWrapper<>(companyAddress)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.companyAddressService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param companyAddress 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody CompanyAddressEntity companyAddress) {
        return new ResponseEntity<>(this.companyAddressService.save(companyAddress), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param companyAddress 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody CompanyAddressEntity companyAddress) {
        return new ResponseEntity<>(this.companyAddressService.updateById(companyAddress), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.companyAddressService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

