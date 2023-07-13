package org.example.modules.tools.storage.controller;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.modules.tools.storage.service.MinioServer;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dou-Fu-10
 * @date 2023-07-07
 * @Description 微服务存储
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storage")
@Slf4j
public class MinioController {

    @Resource
    private MinioServer minioServer;

    @AnonymousPostMapping("/images")
    public String uploadImages(MultipartFile file) {
        log.info("文件上传");
        return minioServer.uploadImages(file);
    }

    @AnonymousPostMapping("/file")
    public String file(MultipartFile file) {
        log.info("文件上传");
        return minioServer.upload(file);
    }

    @AnonymousGetMapping("/test")
    public String uploadImages() {
        return "minioServer.uploadImages(file)";
    }
}
