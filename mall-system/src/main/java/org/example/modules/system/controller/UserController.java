package org.example.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.common.redis.service.RedisService;
import org.example.config.AuthUser;
import org.example.modules.system.entity.UserEntity;
import org.example.modules.system.entity.dto.UserDto;
import org.example.modules.system.entity.vo.UserVo;
import org.example.modules.system.service.UserService;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表控制层
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<UserEntity> page, UserEntity user) {
        redisService.set("111", 12241241);
        redisService.get("111");
        return new ResponseEntity<>(this.userService.page(page, new QueryWrapper<>(user)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.userService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody UserEntity user) {
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody UserEntity user) {
        return new ResponseEntity<>(this.userService.updateById(user), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.userService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }

    /**
     * 用户注册
     *
     * @param resources 注册用户
     * @return 用户信息
     */
    @PostMapping(value = "/register")
    public ResponseEntity<UserVo> register(@Validated @RequestBody UserDto resources) {
        UserEntity userEntity = userService.register(resources);
        return new ResponseEntity<>(BeanCopy.convert(userEntity, UserVo.class), HttpStatus.OK);
    }

    /**
     * 登录以后返回token
     *
     * @param authUser 登录用户
     * @param request  Http Servlet请求
     * @return token
     */
    @Operation(
            summary = "Get user by ID",
            description = "Retrieve user information by their ID"
    )
    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) {
        // 获取 token
        String token = userService.login(authUser, request);
        if (token == null) {
            throw new BaseRequestException("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", token);
        return new ResponseEntity<>(tokenMap, HttpStatus.OK);
    }
}

