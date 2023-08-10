package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.modules.member.entity.MemberLoginLogEntity;
import org.example.modules.member.entity.dto.MemberLoginLogDto;
import org.example.modules.member.entity.vo.MemberCollectionVo;
import org.example.modules.member.entity.vo.MemberLoginLogVo;
import org.example.modules.member.mapper.MemberLoginLogMapper;
import org.example.modules.member.service.MemberLoginLogService;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public Boolean insertLoginLog(Long memberId, HttpServletRequest request) {
        // 获取ip地址
        String ip = StringUtils.getIp(request);
        // 获取Ip对应的地图地址
        String address = StringUtils.getCityInfo(ip);
        String userAgent = StringUtils.getBrowser(request);
        // 插入数据库
        MemberLoginLogEntity memberLoginLogEntity = new MemberLoginLogEntity(memberId, new Date(), ip, null, null, null);
        return memberLoginLogEntity.insert();
    }
}

