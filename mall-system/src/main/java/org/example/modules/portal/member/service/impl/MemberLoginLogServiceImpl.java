package org.example.modules.portal.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.portal.member.entity.MemberLoginLogEntity;
import org.example.modules.portal.member.entity.dto.MemberLoginLogDto;
import org.example.modules.portal.member.entity.vo.MemberLoginLogVo;
import org.example.modules.portal.member.mapper.MemberLoginLogMapper;
import org.example.modules.portal.member.service.MemberLoginLogService;

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
        return (Page) memberLoginLogEntityPageVoIpage;
    }
}

