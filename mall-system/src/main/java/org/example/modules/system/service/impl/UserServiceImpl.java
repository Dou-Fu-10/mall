package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.system.entity.UserEntity;
import org.example.modules.system.entity.dto.UserDto;
import org.example.modules.system.mapper.UserMapper;
import org.example.modules.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表服务实现类
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public UserEntity getByEmail(String email) {
        return lambdaQuery().eq(UserEntity::getEmail, email).one();
    }

    @Override
    public String login(String username, String password) {
        return null;
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

