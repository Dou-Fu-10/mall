package org.example.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.entity.AdminEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.server.MinioServer;
import org.example.common.core.utils.BeanCopy;
import org.example.config.AuthAdmin;
import org.example.config.UpdatePassword;
import org.example.modules.security.service.OnlineAdminService;
import org.example.modules.system.entity.dto.AdminDto;
import org.example.modules.system.entity.vo.AdminVo;
import org.example.modules.system.entity.vo.MenuVo;
import org.example.modules.system.entity.vo.RoleVo;
import org.example.modules.system.mapper.AdminMapper;
import org.example.modules.system.service.AdminLoginLogService;
import org.example.modules.system.service.AdminRolesRelationService;
import org.example.modules.system.service.AdminService;
import org.example.modules.system.service.RolesMenusRelationService;
import org.example.security.config.SecurityProperties;
import org.example.security.entity.JwtAdmin;
import org.example.security.utils.JwtTokenUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-07 09:58:02
 *
 * @author Dou-Fu-10
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
    @Resource
    private OnlineAdminService onlineAdminService;
    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private AdminRolesRelationService adminRolesRelationService;
    @Resource
    private RolesMenusRelationService rolesMenusRelationService;
    @Resource
    private MinioServer minioServer;

    @Override
    public AdminEntity getByEmail(@NotNull String email) {
        return lambdaQuery().eq(AdminEntity::getEmail, email).one();
    }

    @Override
    public Map<String, Object> login(@NotNull AuthAdmin authAdmin, HttpServletRequest request) {

        // 调用 UserDetailsServiceImpl 获取身份信息 同时存储用户信息 判断身份信息是否合法
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authAdmin.getUsername(), authAdmin.getPassword());
        // 对用户的密码进行验证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authentication)) {
            throw new BaseRequestException("用户名或者密码错误");
        }
        // 获取到用户信息
        final JwtAdmin jwtAdmin = (JwtAdmin) authentication.getPrincipal();
        // 使用用户的username作为key获取token
        String token = jwtTokenUtil.createAdminToken(jwtAdmin);
        // 确保token可以 功能非必须
        if (!StringUtils.hasText(token)) {
            throw new BaseRequestException("登录失败");
        }
        Map<String, Object> tokenMap = new HashMap<>(2);
        tokenMap.put("token", securityProperties.getTokenStartWith() + token);
        // 是否唯一登录
        if (securityProperties.getSingleLogin()) {
            // 踢掉之前已经登录的token
            onlineAdminService.kickOutForUsername(authAdmin.getUsername());
        }
        // 保存用户的在线信息
        if (!onlineAdminService.save(jwtAdmin, token, request)) {
            throw new BaseRequestException("登录失败");
        }
        // 记录登录痕迹
        adminLoginLogService.insertLoginLog(authAdmin.getUsername(), request);
        return tokenMap;
    }

    @Override
    public Boolean updatePassword(UpdatePassword updatePassword) {
        AdminEntity adminEntity = lambdaQuery().eq(AdminEntity::getUsername, updatePassword.getUsername()).one();
        if (passwordEncoder.matches(updatePassword.getOldPassword(), adminEntity.getPassword())) {
            adminEntity.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
            return adminEntity.updateById();
        }
        return false;
    }

    @Override
    public Boolean updateRole(Long adminId, @NotNull Set<Long> roleIds) {
        // TODO 数据校验
        if (Objects.isNull(getById(adminId))) {
            return false;
        }
        return adminRolesRelationService.updateRole(adminId, roleIds);
    }

    @Override
    public List<RoleVo> getRoleListByAdminId(Long adminId) {
        return adminRolesRelationService.getRoleListByAdminId(adminId);
    }

    @Override
    public List<MenuVo> getMenuList(Long adminId) {
        List<RoleVo> roleListByAdminId = adminRolesRelationService.getRoleListByAdminId(adminId);
        if (CollectionUtils.isEmpty(roleListByAdminId)) {
            return new ArrayList<>();
        }
        Set<Long> roleId = roleListByAdminId.stream().map(RoleVo::getId).collect(Collectors.toSet());
        return rolesMenusRelationService.findMenusByRoleIds(roleId);
    }

    @Override
    public Boolean updateStatus(Long id, @NotNull Boolean status) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setEnabled(status);
        adminEntity.setId(id);
        // TODO 不允许修改上级的或者同级的
        return adminEntity.updateById();
    }

    @Override
    public Page<AdminVo> page(Page<AdminEntity> page, AdminDto adminDto) {
        AdminEntity adminEntity = BeanCopy.convert(adminDto, AdminEntity.class);
        LambdaQueryWrapper<AdminEntity> adminEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(adminEntity);
        Page<AdminEntity> adminEntityPage = page(page, adminEntityLambdaQueryWrapper);
        IPage<AdminVo> adminVoIpage = adminEntityPage.convert(admin -> BeanCopy.convert(admin, AdminVo.class));
        return (Page) adminVoIpage;
    }

    @Override
    public AdminVo getAdminById(@NotNull Serializable id) {
        AdminEntity adminEntity = getById(id);
        if (Objects.nonNull(adminEntity)) {
            return BeanCopy.convert(adminEntity, AdminVo.class);
        }
        return null;
    }

    @Override
    public String refreshHeadToken(HttpServletRequest request) {
        // 获取token
        String token = jwtTokenUtil.resolveToken(request);
        // 续约token
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public Map<String, Object> info(Principal principal) {
        // TODO 用户登录用户自己
        if (Objects.isNull(principal)) {
            throw new BaseRequestException("请登录！");
        }
        String username = principal.getName();
        AdminEntity adminEntity = getByUsername(username);
        Map<String, Object> data = new HashMap<>(3);
        data.put("username", adminEntity.getUsername());
        // 路由信息
        data.put("menus", getMenuList(adminEntity.getId()));
        // 头像
        data.put("icon", adminEntity.getIcon());
        List<RoleVo> roleList = getRoleListByAdminId(adminEntity.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(RoleVo::getName).collect(Collectors.toList());
            data.put("roles", roles);
        } else {
            data.put("roles", roleList);
        }
        return data;
    }


    @Override
    public AdminEntity getByPhone(@NotNull String phone) {
        return lambdaQuery().eq(AdminEntity::getPhone, phone).one();
    }

    @Override
    public AdminEntity getByUsername(@NotNull String userName) {
        // TODO 做缓存
        return lambdaQuery().eq(AdminEntity::getUsername, userName).one();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean register(@NotNull AdminDto resources) {
        // TODO 对数据进行校验
        AdminEntity user = getByUsername(resources.getUsername());
        // 用户名是否唯一
        if (Objects.nonNull(user)) {
            throw new BaseRequestException("用户名输入错误或用户名已存在");
        }
        // 邮箱是否唯一
        user = getByEmail(resources.getEmail());
        if (Objects.nonNull(user)) {
            throw new BaseRequestException("邮箱输入错误或邮箱已被暂用");
        }
        // 手机号是否唯一
        user = getByPhone(resources.getPhone());
        if (Objects.nonNull(user)) {
            throw new BaseRequestException("手机号输入错误或手机号已被暂用");
        }

        AdminEntity adminEntity = BeanCopy.convert(resources, AdminEntity.class);
        // 设置初始不可登录
        adminEntity.setEnabled(false);
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(resources.getPassword());
        adminEntity.setPassword(encodePassword);
        // 保存到数据库
        if (adminEntity.insert()) {
            if (CollectionUtils.isNotEmpty(resources.getRoleIds())) {
                return adminRolesRelationService.updateRole(adminEntity.getId(), resources.getRoleIds());
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean save(@NotNull AdminDto adminDto) {
        adminDto.setPassword("123456");
        if (Objects.nonNull(adminDto.getIcon()) && minioServer.checkObjectIsExist(adminDto.getIcon())) {
            return register(adminDto);
        }
        adminDto.setIcon(null);
        return register(adminDto);
    }

    @Override
    public Boolean updateById(@NotNull AdminDto adminDto) {
        AdminEntity adminEntity = BeanCopy.convert(adminDto, AdminEntity.class);
        return adminEntity.updateById();
    }
}

