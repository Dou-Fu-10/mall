package org.example.modules.system.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.modules.system.entity.dto.OnlineUserDto;
import org.example.modules.system.service.OnlineUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

/**
 * 系统：在线用户管理
 * @author Zheng Jie
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/online")
public class OnlineController {

    @Resource
    private OnlineUserService onlineUserService;

//    /**
//     * 查询在线用户
//     * @param username
//     * @param pageable
//     * @return
//     */
//    @GetMapping
//    @PreAuthorize("@el.check()")
//    public ResponseEntity<PageResult<OnlineUserDto>> queryOnlineUser(String username, Pageable pageable){
//        return new ResponseEntity<>(onlineUserService.getAll(username, pageable),HttpStatus.OK);
//    }
//
//    /**
//     * 导出数据
//     * @param response
//     * @param username
//     * @throws IOException
//     */
//    @GetMapping(value = "/download")
//    @PreAuthorize("@el.check()")
//    public void exportOnlineUser(HttpServletResponse response, String username) throws IOException {
//        onlineUserService.download(onlineUserService.getAll(username), response);
//    }
//
//    /**
//     * 踢出用户
//     * @param keys
//     * @return
//     * @throws Exception
//     */
//    @DeleteMapping
//    @PreAuthorize("@el.check()")
//    public ResponseEntity<Object> deleteOnlineUser(@RequestBody Set<String> keys) throws Exception {
//        for (String token : keys) {
//            // 解密Key
//            token = EncryptUtils.desDecrypt(token);
//            onlineUserService.logout(token);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
