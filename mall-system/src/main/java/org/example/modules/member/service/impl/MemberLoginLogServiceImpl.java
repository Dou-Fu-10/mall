package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.member.entity.MemberLoginLogEntity;
import org.example.modules.member.mapper.MemberLoginLogMapper;
import org.example.modules.member.service.MemberLoginLogService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:34:48
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:34:48
 * @Description 会员登录记录(MemberLoginLog)表服务实现类
 */
@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLogEntity> implements MemberLoginLogService {

}

