package org.example.modules.security.service.impl;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.FileUtil;
import org.example.common.core.utils.StringUtils;
import org.example.common.redis.service.RedisService;
import org.example.modules.security.service.OnlineUserService;
import org.example.modules.system.entity.vo.OnlineUserVo;
import org.example.security.config.SecurityProperties;
import org.example.security.entity.JwtAdmin;
import org.example.security.entity.OnlineAdminDto;
import org.example.security.utils.JwtTokenUtil;
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
public class OnlineUserServiceImpl implements OnlineUserService {

    @Resource
    private SecurityProperties properties;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private RedisService redisService;

    /**
     * 保存在线用户信息
     *
     * @param jwtAdmin /
     * @param token    /
     * @param request  /
     */
    public Boolean save(JwtAdmin jwtAdmin, String token, HttpServletRequest request) {
        // TODO 优化缓存设计
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
        // 以登录用户的username作为key保存在线用户信息
        return redisService.set(properties.getOnlineKey() + jwtAdmin.getUsername(), onlineAdminDto, properties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
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
    public List<OnlineUserVo> getAll(String username) {
        String loginKey = properties.getOnlineKey() +
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
    public OnlineUserVo getOne(String key) {
        return (OnlineUserVo) redisService.get(key);
    }

    /**
     * 根据用户名强退用户
     *
     * @param username /
     */
    @Async
    public void kickOutForUsername(String username) {
        // TODO 删除用户的 token
        String loginKey = properties.getOnlineKey() + username + "*";
//        redisService.scanDel(loginKey);
    }
}
