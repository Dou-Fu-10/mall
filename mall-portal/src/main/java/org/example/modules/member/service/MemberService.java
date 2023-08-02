package org.example.modules.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.entity.MemberEntity;
import org.example.config.AuthMember;
import org.example.modules.member.entity.dto.MemberDto;
import org.example.modules.member.entity.vo.MemberVo;

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

    MemberVo info(HttpServletRequest request);
}