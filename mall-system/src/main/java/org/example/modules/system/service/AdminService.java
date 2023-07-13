package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.config.AuthUser;
import org.example.config.UpdatePassword;
import org.example.modules.system.entity.AdminEntity;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.dto.AdminDto;

import java.util.List;
import java.util.Map;


/**
 * Created by Dou-Fu-10 2023-07-07 09:58:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(Admin)表服务接口
 */
public interface AdminService extends IService<AdminEntity> {

    /**
     * 注册功能
     *
     * @param resources 注册用户
     * @return Boolean
     */
    Boolean register(AdminDto resources);

    /**
     * 通过用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    AdminEntity getByUsername(String userName);

    /**
     * 通过手机号查询用户信息
     *
     * @param phone 手机号
     * @return 用户信息
     */
    AdminEntity getByPhone(String phone);

    /**
     * 通过邮箱查询用户信息
     *
     * @param email 邮箱
     * @return 用户信息
     */
    AdminEntity getByEmail(String email);

    /**
     * 用户登录
     *
     * @param authUser 用户信息
     * @param request  Http Servlet请求
     * @return token
     */
    Map<String, Object> login(AuthUser authUser, HttpServletRequest request);

    /**
     * 修改密码
     *
     * @param updatePassword 修改密码信息
     * @return 成功状态
     */
    Boolean updatePassword(UpdatePassword updatePassword);

    /**
     * 给用户分配角色
     *
     * @param userId  用户id
     * @param roleIds 角色id列表
     * @return 是否成功
     */
    Boolean updateRole(Long userId, List<Long> roleIds);

    /**
     * 获取用户的角色信息
     *
     * @param userId 用户id
     * @return 角色信息
     */
    List<RoleEntity> getRoleList(Long userId);

    /**
     * 根据用户id 查询用户对应的菜单信息
     *
     * @param id 用户id
     * @return 菜单信息
     */
    List<MenuEntity> getMenuList(Long id);

    /**
     * 修改帐号状态
     *
     * @param adminEntity 信息
     * @return boolean
     */
    Boolean updateStatus(AdminEntity adminEntity);
}
