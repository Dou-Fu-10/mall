package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.member.entity.MemberReceiveAddressEntity;
import org.example.modules.member.mapper.MemberReceiveAddressMapper;
import org.example.modules.member.service.MemberReceiveAddressService;

/**
 * Created by PanShiFu 2023-07-13 15:34:49
 *
 * @author PanShiFu
 * @date 2023-07-13 15:34:49
 * @Description 会员收货地址表(MemberReceiveAddress)表服务实现类
 */
@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressMapper, MemberReceiveAddressEntity> implements MemberReceiveAddressService {

}

