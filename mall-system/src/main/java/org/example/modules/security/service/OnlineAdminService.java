package org.example.modules.security.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.modules.system.entity.vo.OnlineUserVo;
import org.example.security.entity.JwtAdmin;
import org.example.security.entity.OnlineAdminDto;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dou-Fu-10 2023/7/16
 *
 * @author Dou-Fu-10
 * @date 2023/7/16
 * @Description 描述
 */
public interface OnlineAdminService {
    /**
     * 保存在线用户信息
     *
     * @param jwtAdmin /
     * @param token    /
     * @param request  /
     * @return Boolean
     */
    Boolean save(JwtAdmin jwtAdmin, String token, HttpServletRequest request);

    /**
     * 查询全部数据
     *
     * @param username /
     * @param onlineUserDto /
     * @return /
     */
//     Page<OnlineUserVo> getAll(String username, OnlineUserDto onlineUserDto);

    /**
     * 查询全部数据，不分页
     *
     * @param username /
     * @return /
     */
    List<OnlineUserVo> getAll(String username);

    /**
     * 退出登录
     *
     * @param token /
     */
    void logout(String token);

    /**
     * 导出
     *
     * @param all      /
     * @param response /
     * @throws IOException /
     */
    void download(List<OnlineAdminDto> all, HttpServletResponse response) throws IOException;

    /**
     * 查询用户
     *
     * @param key /
     * @return /
     */
    OnlineUserVo getOne(String key);

    /**
     * 根据用户名强退用户
     *
     * @param username /
     */
    @Async
    void kickOutForUsername(String username);
}
