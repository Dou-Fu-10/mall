package org.example.modules.security.service.impl;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.FileUtil;
import org.example.common.core.utils.StringUtils;
import org.example.common.redis.service.RedisService;
import org.example.modules.security.service.OnlineAdminService;
import org.example.modules.system.entity.vo.OnlineUserVo;
import org.example.security.config.SecurityProperties;
import org.example.security.entity.JwtAdmin;
import org.example.security.entity.OnlineAdminDto;
import org.example.security.utils.JwtTokenUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Dou-Fu-10
 * @date 2023-07-16
 * @Description 权限访问配置
 */
@Service
@Slf4j
@AllArgsConstructor
public class OnlineAdminServiceImpl implements OnlineAdminService {

    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private RedisService redisService;

    /**
     * 保存在线用户信息
     *
     * @param jwtAdmin 登录信息
     * @param token    token
     * @param request  请求信息
     */
    @Override
    public Boolean save(@NotNull JwtAdmin jwtAdmin, @NotEmpty(message = "token不能为空") String token, HttpServletRequest request) {
        // 获取登录者 ip
        String ip = StringUtils.getIp(request);
        // 浏览器
        String browser = StringUtils.getBrowser(request);
        // ip 对应的物理地址
        String address = StringUtils.getCityInfo(ip);
        OnlineAdminDto onlineAdminDto = null;
        try {
            onlineAdminDto = new OnlineAdminDto(jwtAdmin, browser, ip, address, token, new Date());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        // 以登录用户的username作为key保存在线用户信息  过期时间以毫秒来计算 14400000  即 4小时
        return redisService.set(securityProperties.getOnlineKey() + jwtAdmin.getUsername(), onlineAdminDto, securityProperties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
    }

    /**
     * 查询全部数据
     *
     * @param username      /
     * @param onlineUserDto /
     * @return /
     */
//    public Page<OnlineUserVo> getAll(String username, OnlineUserDto onlineUserDto) {
//        List<OnlineUserVo> onlineUserDtos = getAll(username);
//        return PageUtil.toPage(
//                PageUtil.paging(pageable.getPageNumber(), pageable.getPageSize(), onlineUserDtos),
//                onlineUserDtos.size()
//        );
//    }

    /**
     * 查询全部数据，不分页
     *
     * @param username /
     * @return /
     */
    @Override
    public List<OnlineUserVo> getAll(String username) {
        String loginKey = securityProperties.getOnlineKey() +
                (StringUtils.isBlank(username) ? "" : "*" + username);
        List<String> keys = redisService.scan(loginKey + "*");
        Collections.reverse(keys);
        List<OnlineAdminDto> onlineAdminDtos = new ArrayList<>();
        for (String key : keys) {
            onlineAdminDtos.add((OnlineAdminDto) redisService.get(key));
        }
        onlineAdminDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return BeanCopy.copytList(onlineAdminDtos, OnlineUserVo.class);
    }

    /**
     * 退出登录
     *
     * @param token /
     */
    @Override
    public void logout(String token) {
        String loginKey = jwtTokenUtil.getUserNameFromToken(token);
        redisService.del(loginKey);
    }

    /**
     * 导出
     *
     * @param all      /
     * @param response /
     */
    @Override
    public void download(List<OnlineAdminDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OnlineAdminDto user : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", user.getJwtAdminDto().getUsername());
            map.put("登录IP", user.getIp());
            map.put("登录地点", user.getAddress());
            map.put("浏览器", user.getBrowser());
            map.put("登录日期", user.getLoginTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 查询用户
     *
     * @param key /
     * @return /
     */
    @Override
    public OnlineUserVo getOne(String key) {
        return (OnlineUserVo) redisService.get(key);
    }

    /**
     * 根据用户名强退用户
     *
     * @param username /
     */
    @Async
    @Override
    public void kickOutForUsername(String username) {
        String loginKey = securityProperties.getOnlineKey() + username;
        redisService.del(loginKey);
    }
}
