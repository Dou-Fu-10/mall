package org.example.modules.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.entity.MemberEntity;
import org.example.config.AuthMember;
import org.example.config.UpdatePassword;
import org.example.modules.member.entity.dto.MemberDto;
import org.example.modules.member.entity.vo.MemberVo;
import org.example.modules.member.entity.vo.ParentOrChildren;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:04
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:04
 * @Description 会员表(Member)表服务接口
 */
public interface MemberService extends IService<MemberEntity> {
    /**
     * 新增数据
     *
     * @param memberDto 实体对象
     * @return 新增结果
     */
    Boolean save(MemberDto memberDto);

    /**
     * 修改数据
     *
     * @param memberDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(MemberDto memberDto);

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param memberDto 查询实体
     * @return 所有数据
     */
    Page<MemberVo> page(Page<MemberEntity> page, MemberDto memberDto);

    /**
     * 用户登录
     *
     * @param authMember 用户信息
     * @param request    Http Servlet请求
     * @return token
     */
    Map<String, Object> login(AuthMember authMember, HttpServletRequest request);

    /**
     * 通过手机号码查询用户信息
     *
     * @param phone 手机
     * @return 会员信息
     */
    MemberEntity getByPhone(String phone);

    /**
     * 获取身份信息
     *
     * @param principal 身份
     * @return 身份信息
     */
    Map<String, Object> info(Principal principal);

    /**
     * token续约
     *
     * @param request token
     * @return token
     */
    HashMap<String, String> refreshHeadToken(HttpServletRequest request);

    /**
     * 注册功能
     *
     * @param authMember 注册用户
     * @return authMember
     */
    Boolean register(AuthMember authMember);

    /**
     * 获取下级
     *
     * @param page 分页数据
     * @return 获取下级
     */
    Page<ParentOrChildren> children(Page<MemberEntity> page);

    /**
     * 获取上级
     *
     * @return 获取上级
     */
    ParentOrChildren parent();

    /**
     * 退出登录
     */
    void logout();

    /**
     * 验证码
     *
     * @return /
     */
    Map<String, Object> generateVerificationCode();

    /**
     * @return /
     */
    Object sendSMS();
    /**
     * 修改密码
     *
     * @param updatePassword 修改密码信息
     * @return 成功状态
     */
    Boolean updatePassword(UpdatePassword updatePassword);
}
