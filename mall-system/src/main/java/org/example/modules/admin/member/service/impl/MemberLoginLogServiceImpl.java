package org.example.modules.admin.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.admin.member.entity.MemberLoginLogEntity;
import org.example.modules.admin.member.entity.dto.MemberLoginLogDto;
import org.example.modules.admin.member.mapper.MemberLoginLogMapper;
import org.example.modules.admin.member.service.MemberLoginLogService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:17
 * @Description 会员登录记录(MemberLoginLog)表服务实现类
 */
@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLogEntity> implements MemberLoginLogService {
    @Override
    public boolean save(MemberLoginLogDto MemberLoginLog) {
        return false;
    }

    @Override
    public boolean updateById(MemberLoginLogDto MemberLoginLog) {
        return false;
    }
}

