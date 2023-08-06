package org.example.modules.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.member.entity.MemberReceiveAddressEntity;
import org.example.modules.member.entity.dto.MemberReceiveAddressDto;
import org.example.modules.member.entity.vo.MemberReceiveAddressVo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员收货地址表(MemberReceiveAddress)表服务接口
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {
    /**
     * 新增数据
     *
     * @param memberReceiveAddressDto 实体对象
     * @return 新增结果
     */
    Boolean save(MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     * 修改数据
     *
     * @param memberReceiveAddressDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     * 分页查询所有数据
     *
     * @param page                    分页对象
     * @param memberReceiveAddressDto 查询实体
     * @return 所有数据
     */
    Page<MemberReceiveAddressVo> page(Page<MemberReceiveAddressEntity> page, MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     * 通过会员id获取会员的地址信息
     *
     * @param memberId 会员id
     * @return 所有数据
     */
    List<MemberReceiveAddressVo> getReceiveAddressByMemberId(Serializable memberId);

    /**
     * 通过地址id 和 用户id 精确 地址
     *
     * @param memberReceiveAddressId 地址id
     * @param memberId               用户id
     * @return 地址
     */
    MemberReceiveAddressVo getReceiveAddressByMemberIdAndMemberReceiveAddressId(Long memberReceiveAddressId, Long memberId);

    MemberReceiveAddressVo selectOne(Serializable id);

    List<MemberReceiveAddressVo> selectAll();

}
