package org.example.modules.comment.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.comment.entity.dto.CommentReplayDto;
import org.example.modules.comment.service.CommentReplayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dou-Fu-10 2023-08-10 15:24:17
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 15:24:17
 * @Description 产品评价回复表(CommentReplay)表控制层
 */
@RestController
@RequestMapping("/app/commentReplay")
@Tag(name = "CommentReplayController", description = "")
public class CommentReplayController {
    /**
     * 服务对象
     */
    @Resource
    private CommentReplayService commentReplayService;

    /**
     * 新增数据
     *
     * @param commentReplayDto 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody CommentReplayDto commentReplayDto) {
        if (this.commentReplayService.save(commentReplayDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }
}

