package org.example.modules.comment.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.comment.entity.CommentReplayEntity;
import org.example.modules.comment.entity.dto.CommentReplayDto;
import org.example.modules.comment.service.CommentReplayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 产品评价回复表 CommentReplayController
 * Created by Dou-Fu-10 2023-07-13 21:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:11
 * @Description 产品评价回复表(CommentReplay)表控制层
 */
@RestController
@RequestMapping("/api/commentReplay")
@Tag(name = "CommentReplayController", description = "产品评价回复表(CommentReplay)表控制层")
public class CommentReplayController {
    /**
     * 服务对象
     */
    @Resource
    private CommentReplayService commentReplayService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param commentReplayDto 查询实体
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @GetMapping
    @PreAuthorize("@hasPermission.check('commentReplay::select')")
    public ResponseEntity<Object> select(Page<CommentReplayEntity> page, CommentReplayDto commentReplayDto) {
        return new ResponseEntity<>(this.commentReplayService.page(page, commentReplayDto), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "分页查询所有数据")
    @GetMapping("{id}")
    @PreAuthorize("@hasPermission.check('commentReplay::select')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.commentReplayService.getByCommentReplayId(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param commentReplayDto 实体对象
     * @return 新增结果
     */
    @Operation(summary = "分页查询所有数据")
    @PostMapping
    @PreAuthorize("@hasPermission.check('commentReplay::select')")
    public ResponseEntity<Object> insert(@RequestBody CommentReplayDto commentReplayDto) {
        if (this.commentReplayService.save(commentReplayDto)) {
            return new ResponseEntity<>("新增成功", HttpStatus.OK);
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "分页查询所有数据")
    @DeleteMapping
    @PreAuthorize("@hasPermission.check('commentReplay::select')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (this.commentReplayService.removeByIds(ids)) {
            return new ResponseEntity<>("删除成功", HttpStatus.OK);
        }
        throw new BaseRequestException("删除失败");
    }
}

