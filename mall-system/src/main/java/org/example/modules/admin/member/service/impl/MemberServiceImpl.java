package org.example.modules.admin.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.admin.member.entity.MemberEntity;
import org.example.modules.admin.member.entity.dto.MemberDto;
import org.example.modules.admin.member.mapper.MemberMapper;
import org.example.modules.admin.member.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:16
 * @Description 会员表(Member)表服务实现类
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {
    @Override
    public boolean save(MemberDto Member) {
        return false;
    }

    @Override
    public boolean updateById(MemberDto Member) {
        return false;
    }
}

