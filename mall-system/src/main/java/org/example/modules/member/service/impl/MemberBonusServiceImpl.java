package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.member.entity.MemberBonusEntity;
import org.example.modules.member.mapper.MemberBonusMapper;
import org.example.modules.member.service.MemberBonusService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 奖金池每天收益(MemberBonus)表服务实现类
 * Created by Dou-Fu-10 2023-08-13 15:09:49
 *
 * @author Dou-Fu-10
 * @date 2023-08-13 15:09:49
 * @Description 奖金池每天收益(MemberBonus)表服务实现类
 */
@Service("memberBonusService")
public class MemberBonusServiceImpl extends ServiceImpl<MemberBonusMapper, MemberBonusEntity> implements MemberBonusService {

    @Override
    public Boolean save(BigDecimal bonus) {
        MemberBonusEntity memberBonusEntity = new MemberBonusEntity();
        memberBonusEntity.setMemberBonus(bonus);
        return save(memberBonusEntity);
    }
}

