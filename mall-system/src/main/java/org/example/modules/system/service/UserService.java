package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.config.AuthUser;
import org.example.modules.system.entity.UserEntity;
import org.example.modules.system.entity.dto.UserDto;


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
     * @return 用户信息
     */
    UserEntity register(UserDto resources);

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
    String login(AuthUser authUser, HttpServletRequest request);

}
