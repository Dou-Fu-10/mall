package org.example.modules.comment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.modules.comment.entity.CommentReplayEntity;
import org.example.modules.comment.service.CommentReplayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-13 15:19:46
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:19:46
 * @Description 产品评价回复表(CommentReplay)表控制层
 */
@RestController
@RequestMapping("/api/commentReplay")
public class CommentReplayController {
    /**
     * 服务对象
     */
    @Resource
    private CommentReplayService commentReplayService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param commentReplay 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<CommentReplayEntity> page, CommentReplayEntity commentReplay) {
        return new ResponseEntity<>(this.commentReplayService.page(page, new QueryWrapper<>(commentReplay)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.commentReplayService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param commentReplay 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody CommentReplayEntity commentReplay) {
        return new ResponseEntity<>(this.commentReplayService.save(commentReplay), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param commentReplay 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody CommentReplayEntity commentReplay) {
        return new ResponseEntity<>(this.commentReplayService.updateById(commentReplay), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.commentReplayService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

