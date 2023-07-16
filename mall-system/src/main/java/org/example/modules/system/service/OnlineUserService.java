package org.example.modules.system.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.modules.system.entity.dto.OnlineUserDto;
import org.example.modules.system.entity.vo.OnlineUserVo;
import org.example.security.entity.JwtUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.List;

/**
 * Created by PanShiFu 2023/7/16
 *
 * @author PanShiFu
 * @date 2023/7/16
 * @Description 描述
 */
public interface OnlineUserService {
    /**
     * 保存在线用户信息
     *
     * @param jwtUser /
     * @param token      /
     * @param request    /
     */
    public void save(JwtUser jwtUser, String token, HttpServletRequest request);

    /**
     * 查询全部数据
     *
     * @param username /
     * @param onlineUserDto /
     * @return /
     */
//    public Page<OnlineUserVo> getAll(String username, OnlineUserDto onlineUserDto);

    /**
     * 查询全部数据，不分页
     *
     * @param username /
     * @return /
     */
    public List<OnlineUserVo> getAll(String username);

    /**
     * 退出登录
     *
     * @param token /
     */
    public void logout(String token);

    /**
     * 导出
     *
     * @param all      /
     * @param response /
     * @throws IOException /
     */
    public void download(List<OnlineUserDto> all, HttpServletResponse response) throws IOException;

    /**
     * 查询用户
     *
     * @param key /
     * @return /
     */
    public OnlineUserVo getOne(String key);

    /**
     * 根据用户名强退用户
     *
     * @param username /
     */
    @Async
    public void kickOutForUsername(String username);
}
