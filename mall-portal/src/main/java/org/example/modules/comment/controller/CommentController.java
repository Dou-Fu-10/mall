package org.example.modules.comment.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.comment.entity.CommentEntity;
import org.example.modules.comment.entity.dto.CommentDto;
import org.example.modules.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 商品评价表 CommentController
 * Created by Dou-Fu-10 2023-08-10 15:24:16
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 15:24:16
 * @Description 商品评价表(Comment)表控制层
 */
@RestController
@RequestMapping("/app/comment")
@Tag(name = "CommentController", description = "")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param productId 商品id
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @GetMapping
    public ResponseEntity<Object> select(Page<CommentEntity> page, Long productId) {
        CommentDto commentDto = new CommentDto();
        commentDto.setProductId(productId);
        commentDto.setShowStatus(true);
        return ResponseEntity.ok(this.commentService.page(page, commentDto));
    }

    /**
     * 新增数据
     *
     * @param commentDto 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody CommentDto commentDto) {
        if (this.commentService.save(commentDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }
}

