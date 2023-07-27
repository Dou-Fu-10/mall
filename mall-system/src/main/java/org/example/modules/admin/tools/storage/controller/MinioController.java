package org.example.modules.admin.tools.storage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.example.modules.admin.tools.storage.service.MinioServer;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Base64;

/**
 * @author Dou-Fu-10
 * @date 2023-07-07
 * @Description 微服务存储
 */
@Slf4j
@RestController
@RequestMapping("/api/storage")
@Tag(name = "MinioController", description = "微服务存储")
public class MinioController {

    @Resource
    private MinioServer minioServer;

    /**
     * 上传图片
     *
     * @param file 图片
     * @return 图片路径
     */
    @Operation(summary = "上传图片", description = "上传图片")
    @AnonymousPostMapping("/images")
    public String uploadImages(MultipartFile file) {
        log.info("图片上传");
        return minioServer.uploadImages(file);
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件路径
     */
    @Operation(summary = "上传文件", description = "上传文件")
    @AnonymousPostMapping("/file")
    public String file(MultipartFile file) {
        log.info("文件上传");
        return minioServer.upload(file);
    }

    /**
     * 浏览图片
     *
     * @param fileName 图片名字
     * @return 浏览图片
     */
    @Operation(summary = "浏览图片", description = "浏览图片")
    @AnonymousGetMapping("/browse")
    public ResponseEntity<String> browse(String fileName) {
        InputStream objectInputStream = minioServer.getObjectInputStream(fileName);
        return ResponseEntity.ok(toBase64(objectInputStream));
    }

    /**
     * InputStream转Base64
     *
     * @param inputStream
     * @return
     */
    public static String toBase64(InputStream inputStream) {
        try (inputStream) {
            //转换为base64
            byte[] bytes = IOUtils.toByteArray(inputStream);
            inputStream.close();
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
