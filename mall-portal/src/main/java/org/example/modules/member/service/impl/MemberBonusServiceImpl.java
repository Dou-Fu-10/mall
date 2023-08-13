package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberBonusEntity;
import org.example.modules.member.entity.vo.MemberBonusVo;
import org.example.modules.member.mapper.MemberBonusMapper;
import org.example.modules.member.service.MemberBonusService;

/**
 * 奖金池每天收益(MemberBonus)表服务实现类
 * Created by Dou-Fu-10 2023-08-13 15:14:54
 *
 * @author Dou-Fu-10
 * @date 2023-08-13 15:14:54
 * @Description 奖金池每天收益(MemberBonus)表服务实现类
 */
@Service("memberBonusService")
public class MemberBonusServiceImpl extends ServiceImpl<MemberBonusMapper, MemberBonusEntity> implements MemberBonusService {

    @Override
    public MemberBonusVo getMemberBonus() {
        // 创建 LambdaQueryWrapper 对象
        LambdaQueryWrapper<MemberBonusEntity> memberBonusEntityLambdaQueryWrapper = Wrappers.lambdaQuery();
        // 按照时间降序排列
        memberBonusEntityLambdaQueryWrapper.orderByDesc(MemberBonusEntity::getCreateTime);
        memberBonusEntityLambdaQueryWrapper.last("LIMIT 1");
        MemberBonusEntity memberBonus = getOne(memberBonusEntityLambdaQueryWrapper);
        return BeanCopy.convert(memberBonus, MemberBonusVo.class);
    }
}

