package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.config.AuthUser;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.UserEntity;
import org.example.modules.system.entity.dto.UpdatePasswordDto;
import org.example.modules.system.entity.dto.UserDto;

import java.util.List;
import java.util.Map;


/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表服务接口
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 注册功能
     *
     * @param resources 注册用户
     * @return Boolean
     */
    Boolean register(UserDto resources);

    /**
     * 通过用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    UserEntity getByUsername(String userName);

    /**
     * 通过手机号查询用户信息
     *
     * @param phone 手机号
     * @return 用户信息
     */
    UserEntity getByPhone(String phone);

    /**
     * 通过邮箱查询用户信息
     *
     * @param email 邮箱
     * @return 用户信息
     */
    UserEntity getByEmail(String email);

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
     * @param updatePasswordDto 修改密码信息
     * @return 成功状态
     */
    Boolean updatePassword(UpdatePasswordDto updatePasswordDto);

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
     * @param user 信息
     * @return boolean
     */
    Boolean updateStatus(UserEntity user);
}
