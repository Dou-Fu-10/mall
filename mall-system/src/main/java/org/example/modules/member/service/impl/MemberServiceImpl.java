package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.member.entity.MemberEntity;
import org.example.modules.member.entity.dto.MemberDto;
import org.example.modules.member.mapper.MemberMapper;
import org.example.modules.member.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-14 14:34:16
 *
 * @author PanShiFu
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

