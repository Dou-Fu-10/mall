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
import org.example.modules.system.entity.dto.OnlineUserDto;
import org.example.modules.system.entity.vo.OnlineUserVo;
import org.example.security.config.SecurityProperties;
import org.example.security.entity.JwtUser;
import org.example.security.utils.JwtTokenUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ty
 */
@Service
@Slf4j
@AllArgsConstructor
public class OnlineUserServiceImpl implements OnlineUserService {

    @Resource
    private  SecurityProperties properties;
    @Resource
    private  JwtTokenUtil jwtTokenUtil;
    @Resource
    private  RedisService redisService;

    /**
     * 保存在线用户信息
     *
     * @param jwtUserDto /
     * @param token      /
     * @param request    /
     */
    public void save(JwtUser jwtUserDto, String token, HttpServletRequest request) {
        // TODO 优化缓存设计
        // 获取登录者 ip
        String ip = StringUtils.getIp(request);
        // 浏览器
        String browser = StringUtils.getBrowser(request);
        // ip 对应的物理地址
        String address = StringUtils.getCityInfo(ip);
        OnlineUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDto(jwtUserDto.getUsername(), jwtUserDto.getUser().getNickName(), browser, ip, address, token, new Date());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        // 获取登录名字
        String loginKey = jwtTokenUtil.getUserNameFromToken(token);
        redisService.set(loginKey, onlineUserDto, properties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
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
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
        for (String key : keys) {
            onlineUserDtos.add((OnlineUserDto) redisService.get(key));
        }
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return BeanCopy.copytList(onlineUserDtos, OnlineUserVo.class);
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
    public void download(List<OnlineUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OnlineUserDto user : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", user.getUserName());
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
        String loginKey = properties.getOnlineKey() + username + "*";
//        redisService.scanDel(loginKey);
    }
}
