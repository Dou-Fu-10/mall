package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.config.AuthUser;
import org.example.config.JwtUser;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.AdminEntity;
import org.example.config.UpdatePassword;
import org.example.modules.system.entity.dto.AdminDto;
import org.example.modules.system.mapper.AdminMapper;
import org.example.modules.system.service.AdminLoginLogService;
import org.example.modules.system.service.AdminService;
import org.example.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表服务实现类
 */
@Service("adminService")
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Resource
    private AdminLoginLogService adminLoginLogService;

    @Override
    public AdminEntity getByEmail(String email) {
        return lambdaQuery().eq(AdminEntity::getEmail, email).one();
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
            tokenMap.put("token", token);
            // 记录登录 信息
            adminLoginLogService.insertLoginLog(authUser.getUsername(), request);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return tokenMap;
    }

    @Override
    public Boolean updatePassword(UpdatePassword updatePassword) {
        return false;
    }

    @Override
    public Boolean updateRole(Long userId, List<Long> roleIds) {
        // TODO 不允许修改上级的或者同级的
        return false;
    }

    @Override
    public List<RoleEntity> getRoleList(Long userId) {
        return null;
    }

    @Override
    public List<MenuEntity> getMenuList(Long id) {
        return null;
    }

    @Override
    public Boolean updateStatus(AdminEntity adminEntity) {
        // TODO 不允许修改上级的或者同级的
        return null;
    }


    @Override
    public AdminEntity getByPhone(String phone) {
        return lambdaQuery().eq(AdminEntity::getPhone, phone).one();
    }

    @Override
    public AdminEntity getByUsername(String userName) {
        // TODO 做缓存
        return lambdaQuery().eq(AdminEntity::getUsername, userName).one();
    }

    @Override
    public Boolean register(AdminDto resources) {
        // TODO 对数据进行校验
        AdminEntity user = getByUsername(resources.getUsername());
        // 用户名是否唯一
        if (Objects.isNull(user)) {
            throw new BaseRequestException("用户名输入错误或用户名已存在");
        }
        // 邮箱是否唯一
        user = getByEmail(resources.getEmail());
        if (Objects.isNull(user)) {
            throw new BaseRequestException("邮箱输入错误或邮箱已被暂用");
        }
        // 手机号是否唯一
        user = getByPhone(resources.getPhone());
        if (Objects.isNull(user)) {
            throw new BaseRequestException("手机号输入错误或手机号已被暂用");
        }

        AdminEntity adminEntity = BeanCopy.convert(resources, AdminEntity.class);
        // 设置初始不可登录
        adminEntity.setEnabled(false);
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(resources.getPassword());
        adminEntity.setPassword(encodePassword);
        // 保存到数据库
        return adminEntity.insert();
    }
}

