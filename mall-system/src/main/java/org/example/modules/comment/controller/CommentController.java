package org.example.modules.comment.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.comment.entity.CommentEntity;
import org.example.modules.comment.entity.dto.CommentDto;
import org.example.modules.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商品评价表 CommentController
 * Created by Dou-Fu-10 2023-07-13 21:35:10
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:10
 * @Description 商品评价表(Comment)表控制层
 */
@RestController
@RequestMapping("/api/comment")
@Tag(name = "CommentController", description = "商品评价表(Comment)表控制层")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param commentDto 查询实体
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @GetMapping
    public ResponseEntity<Object> select(Page<CommentEntity> page, CommentDto commentDto) {
        return new ResponseEntity<>(this.commentService.page(page, commentDto), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "分页查询所有数据")
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.commentService.getByCommentId(id), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "分页查询所有数据")
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(ids)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<CommentEntity> collect = ids.stream().map(id -> new CommentEntity(id, false)).collect(Collectors.toSet());
        if (this.commentService.updateBatchById(collect)) {
            return new ResponseEntity<>("删除成功", HttpStatus.OK);
        }
        throw new BaseRequestException("删除失败");
    }
}

