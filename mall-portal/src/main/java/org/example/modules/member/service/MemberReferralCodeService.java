package org.example.modules.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.member.entity.MemberReferralCodeEntity;
import org.example.modules.member.entity.vo.MemberReferralCodeVo;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-08-09 12:46:30
 *
 * @author Dou-Fu-10
 * @date 2023-08-09 12:46:30
 * @Description 推荐码(MemberReferralCode)表服务接口
 */
public interface MemberReferralCodeService extends IService<MemberReferralCodeEntity> {
    /**
     * 新增数据
     *
     * @return 新增结果
     */
    Boolean save();

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    Page<MemberReferralCodeVo> page(Page<MemberReferralCodeEntity> page);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    MemberReferralCodeVo getByMemberReferralCodeId(Serializable id);

    /**
     * 通过主键查询单条数据
     *
     * @param code 主键
     * @return 单条数据
     */
    MemberReferralCodeVo getByCode(String code);
}
