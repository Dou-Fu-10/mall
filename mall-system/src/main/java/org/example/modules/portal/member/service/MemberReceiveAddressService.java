package org.example.modules.portal.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.portal.member.entity.MemberReceiveAddressEntity;
import org.example.modules.portal.member.entity.dto.MemberReceiveAddressDto;
import org.example.modules.portal.member.entity.vo.MemberReceiveAddressVo;

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
}
