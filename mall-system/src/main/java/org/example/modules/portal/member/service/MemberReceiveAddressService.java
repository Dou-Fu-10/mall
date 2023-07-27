package org.example.modules.portal.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.portal.member.entity.MemberReceiveAddressEntity;
import org.example.modules.portal.member.entity.dto.MemberReceiveAddressDto;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:17
 * @Description 会员收货地址表(MemberReceiveAddress)表服务接口
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {
    /**
     * 新增数据
     *
     * @param MemberReceiveAddress 实体对象
     * @return 新增结果
     */
    boolean save(MemberReceiveAddressDto MemberReceiveAddress);

    /**
     * 修改数据
     *
     * @param MemberReceiveAddress 实体对象
     * @return 修改结果
     */
    boolean updateById(MemberReceiveAddressDto MemberReceiveAddress);
}
