package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.member.entity.MemberLoginLogEntity;
import org.example.modules.member.entity.dto.MemberLoginLogDto;
import org.example.modules.member.mapper.MemberLoginLogMapper;
import org.example.modules.member.service.MemberLoginLogService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-14 14:34:17
 *
 * @author PanShiFu
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

