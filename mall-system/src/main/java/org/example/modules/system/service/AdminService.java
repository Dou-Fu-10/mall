package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.entity.AdminEntity;
import org.example.config.AuthAdmin;
import org.example.config.UpdatePassword;
import org.example.modules.system.entity.dto.AdminDto;
import org.example.modules.system.entity.vo.AdminVo;
import org.example.modules.system.entity.vo.MenuVo;
import org.example.modules.system.entity.vo.RoleVo;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
     * 新增数据
     *
     * @param adminDto 实体对象
     * @return 新增结果
     */
    Boolean save(AdminDto adminDto);

    /**
     * 修改数据
     *
     * @param adminDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(AdminDto adminDto);

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
     * @param authAdmin 用户信息
     * @param request   Http Servlet请求
     * @return token
     */
    Map<String, Object> login(AuthAdmin authAdmin, HttpServletRequest request);

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
     * @param adminId 用户id
     * @param roleIds 角色id列表
     * @return 是否成功
     */
    Boolean updateRole(Long adminId, Set<Long> roleIds);

    /**
     * 获取用户的角色信息
     *
     * @param adminId 用户id
     * @return 角色信息
     */
    List<RoleVo> getRoleListByAdminId(Long adminId);

    /**
     * 根据用户id 查询用户对应的菜单信息
     *
     * @param adminId 用户id
     * @return 菜单信息
     */
    List<MenuVo> getMenuList(Long adminId);

    /**
     * 修改帐号状态
     *
     * @param id     用户id
     * @param status 状态
     * @return boolean
     */
    Boolean updateStatus(Long id, Boolean status);

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param adminDto 查询实体
     * @return 所有数据
     */
    Page<AdminVo> page(Page<AdminEntity> page, AdminDto adminDto);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    AdminVo getAdminById(Serializable id);

    /**
     * token续约
     *
     * @param request token
     * @return token
     */
    HashMap<String, String> refreshHeadToken(HttpServletRequest request);

    /**
     * 获取当前登录用户信息
     *
     * @param principal 主要
     * @return 用户登录信息
     */
    Map<String, Object> info(Principal principal);

    /**
     * 退出登录
     */
    void logout();

}
