package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.member.entity.MemberPriceEntity;
import org.example.modules.member.mapper.MemberPriceMapper;
import org.example.modules.member.service.MemberPriceService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:17
 * @Description 商品会员价格表(MemberPrice)表服务实现类
 */
@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceMapper, MemberPriceEntity> implements MemberPriceService {

}

