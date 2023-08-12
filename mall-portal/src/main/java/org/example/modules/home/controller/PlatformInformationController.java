package org.example.modules.home.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.home.entity.PlatformInformationEntity;
import org.example.modules.home.entity.dto.PlatformInformationDto;
import org.example.modules.home.entity.vo.PlatformInformationVo;
import org.example.modules.home.service.PlatformInformationService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 平台信息 PlatformInformationController
 * Created by Dou-Fu-10 2023-07-31 22:28:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 22:28:56
 * @Description 平台信息(PlatformInformation)表控制层
 */
@RestController
@RequestMapping("/platformInformation")
@Tag(name = "PlatformInformationController", description = "")
public class PlatformInformationController {
    /**
     * 服务对象
     */
    @Resource
    private PlatformInformationService platformInformationService;

    /**
     * 分页查询所有数据
     *
     * @param page                   分页对象
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @AnonymousGetMapping
    public ResponseEntity<Object> select(Page<PlatformInformationEntity> page ) {
        return ResponseEntity.ok(this.platformInformationService.page(page, new PlatformInformationDto()).getRecords());
    }
}

