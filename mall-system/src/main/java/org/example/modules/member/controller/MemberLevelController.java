package org.example.modules.member.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.modules.member.entity.MemberLevelEntity;
import org.example.modules.member.service.MemberLevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-13 15:34:48
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:34:48
 * @Description 会员等级表(MemberLevel)表控制层
 */
@RestController
@RequestMapping("/api/memberLevel")
public class MemberLevelController {
    /**
     * 服务对象
     */
    @Resource
    private MemberLevelService memberLevelService;

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param memberLevel 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<MemberLevelEntity> page, MemberLevelEntity memberLevel) {
        return new ResponseEntity<>(this.memberLevelService.page(page, new QueryWrapper<>(memberLevel)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.memberLevelService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param memberLevel 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody MemberLevelEntity memberLevel) {
        return new ResponseEntity<>(this.memberLevelService.save(memberLevel), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param memberLevel 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody MemberLevelEntity memberLevel) {
        return new ResponseEntity<>(this.memberLevelService.updateById(memberLevel), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.memberLevelService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

