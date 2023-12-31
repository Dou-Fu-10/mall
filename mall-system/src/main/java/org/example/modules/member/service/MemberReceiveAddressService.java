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
     * 分页查询所有数据
     *
     * @param page                    分页对象
     * @param memberReceiveAddressDto 查询实体
     * @return 所有数据
     */
    Page<MemberReceiveAddressVo> page(Page<MemberReceiveAddressEntity> page, MemberReceiveAddressDto memberReceiveAddressDto);

    /**
     * 通过会员id 查询所有数据
     *
     * @param id 会员id
     * @return 所有数据
     */
    List<MemberReceiveAddressVo> getByMemberId(Serializable id);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 所有数据
     */
    MemberReceiveAddressVo getByMemberReceiveAddressId(Serializable id);
}
