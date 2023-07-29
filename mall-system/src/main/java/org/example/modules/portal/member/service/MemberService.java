package org.example.modules.portal.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.portal.member.entity.MemberEntity;
import org.example.modules.portal.member.entity.dto.MemberDto;
import org.example.modules.portal.member.entity.vo.MemberVo;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:16
 * @Description 会员表(Member)表服务接口
 */
public interface MemberService extends IService<MemberEntity> {
    /**
     * 新增数据
     *
     * @param member 实体对象
     * @return 新增结果
     */
    boolean save(MemberDto member);

    /**
     * 修改数据
     *
     * @param member 实体对象
     * @return 修改结果
     */
    boolean updateById(MemberDto member);
    /**
     * 分页查询所有数据
     *
     * @param page   分页对象
     * @param member 查询实体
     * @return 所有数据
     */
    Page<MemberVo> page(Page<MemberEntity> page, MemberEntity member);
}
