package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.config.AuthUser;
import org.example.config.JwtUser;
import org.example.modules.system.entity.UserEntity;
import org.example.modules.system.entity.dto.UserDto;
import org.example.modules.system.mapper.UserMapper;
import org.example.modules.system.service.AdminLoginLogService;
import org.example.modules.system.service.UserService;
import org.example.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表服务实现类
 */
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Resource
    private AdminLoginLogService adminLoginLogService;

    @Override
    public UserEntity getByEmail(String email) {
        return lambdaQuery().eq(UserEntity::getEmail, email).one();
    }

    @Override
    public Map<String, Object> login(AuthUser authUser, HttpServletRequest request) {
        Map<String, Object> tokenMap = new HashMap<>(2);
        try {
            // 调用 UserDetailsServiceImpl 获取身份信息
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword());
            // 对身份进行验证
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            // 判断是否认证通过
            if (Objects.isNull(authentication)) {
                throw new BaseRequestException("用户名或者密码错误");
            }
            // 放入 SecurityContextHolder 以便后续使用
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            // 获取到用户信息
            final JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
            // 获取token
            String token = jwtTokenUtil.generateToken(jwtUser);
            tokenMap.put("user", jwtUser);
            tokenMap.put("token", token);
            // 记录登录 信息
            adminLoginLogService.insertLoginLog(authUser.getUsername(), request);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return tokenMap;
    }

    @Override
    public UserEntity getByPhone(String phone) {
        return lambdaQuery().eq(UserEntity::getPhone, phone).one();
    }

    @Override
    public UserEntity getByUsername(String userName) {
        return lambdaQuery().eq(UserEntity::getUsername, userName).one();
    }

    @Override
    public UserEntity register(UserDto resources) {
        UserEntity user = getByUsername(resources.getUsername());
        // 用户名是否唯一
        if (Objects.isNull(user)) {
            throw new BaseRequestException("用户名输入错误或用户名已存在");
        }
        // 优先是否唯一
        user = getByEmail(resources.getEmail());
        if (Objects.isNull(user)) {
            throw new BaseRequestException("邮箱输入错误或邮箱已被暂用");
        }
        // 手机号是否唯一
        user = getByPhone(resources.getPhone());
        if (Objects.isNull(user)) {
            throw new BaseRequestException("手机号输入错误或手机号已被暂用");
        }

        UserEntity userEntity = BeanCopy.convert(resources, UserEntity.class);
        // 设置初始不可登录
        userEntity.setEnabled(false);
        // 将密码进行加密操作
        // String encodePassword = passwordEncoder.encode(resources.getPassword());
        userEntity.setPassword("123456");
        // 保存到数据量
        userEntity.insert();
        return null;
    }
}

