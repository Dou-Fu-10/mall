package org.example;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.core.entity.MemberEntity;
import org.example.modules.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MallPortalApplication.class)
public class MallTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void testUser(){
        LambdaQueryWrapper<MemberEntity> memberEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        memberEntityLambdaQueryWrapper.eq(MemberEntity::getInvitationCode,"1");
        List<MemberEntity> list = memberService.list(memberEntityLambdaQueryWrapper);
        list.forEach(memberEntity -> memberEntity.setInvitationCode(RandomUtil.randomString(6)));
        memberService.updateBatchById(list);
    }
}
