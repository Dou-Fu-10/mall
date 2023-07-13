package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.member.entity.MemberEntity;
import org.example.modules.member.mapper.MemberMapper;
import org.example.modules.member.service.MemberService;

/**
 * Created by PanShiFu 2023-07-13 14:28:43
 *
 * @author PanShiFu
 * @date 2023-07-13 14:28:43
 * @Description 会员表(Member)表服务实现类
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {

}

