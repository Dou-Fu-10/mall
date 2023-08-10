package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberLoginLogEntity;
import org.example.modules.member.entity.dto.MemberLoginLogDto;
import org.example.modules.member.entity.vo.MemberLoginLogVo;
import org.example.modules.member.mapper.MemberLoginLogMapper;
import org.example.modules.member.service.MemberLoginLogService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员登录记录(MemberLoginLog)表服务实现类
 */
@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLogEntity> implements MemberLoginLogService {
    @Override
    public Boolean save(MemberLoginLogDto memberLoginLogDto) {
        MemberLoginLogEntity memberLoginLogEntity = BeanCopy.convert(memberLoginLogDto, MemberLoginLogEntity.class);
        return save(memberLoginLogEntity);
    }

    @Override
    public Boolean updateById(MemberLoginLogDto memberLoginLogDto) {
        MemberLoginLogEntity memberLoginLogEntity = BeanCopy.convert(memberLoginLogDto, MemberLoginLogEntity.class);
        return updateById(memberLoginLogEntity);
    }

    @Override
    public Page<MemberLoginLogVo> page(Page<MemberLoginLogEntity> page, MemberLoginLogDto memberLoginLogDto) {
        MemberLoginLogEntity memberLoginLogEntity = BeanCopy.convert(memberLoginLogDto, MemberLoginLogEntity.class);
        LambdaQueryWrapper<MemberLoginLogEntity> memberLoginLogEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberLoginLogEntity);
        Page<MemberLoginLogEntity> memberLoginLogEntityPage = page(page, memberLoginLogEntityLambdaQueryWrapper);
        IPage<MemberLoginLogVo> memberLoginLogEntityPageVoIpage = memberLoginLogEntityPage.convert(memberLoginLog -> BeanCopy.convert(memberLoginLog, MemberLoginLogVo.class));
        return (Page<MemberLoginLogVo>) memberLoginLogEntityPageVoIpage;
    }
}

