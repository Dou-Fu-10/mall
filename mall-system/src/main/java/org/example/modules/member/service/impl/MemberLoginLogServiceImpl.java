package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.member.entity.MemberLoginLogEntity;
import org.example.modules.member.mapper.MemberLoginLogMapper;
import org.example.modules.member.service.MemberLoginLogService;

/**
 * Created by PanShiFu 2023-07-13 14:08:44
 *
 * @author PanShiFu
 * @date 2023-07-13 14:08:44
 * @Description 会员登录记录(MemberLoginLog)表服务实现类
 */
@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLogEntity> implements MemberLoginLogService {

}

