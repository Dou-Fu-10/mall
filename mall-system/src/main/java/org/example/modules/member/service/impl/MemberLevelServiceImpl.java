package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.member.entity.MemberLevelEntity;
import org.example.modules.member.mapper.MemberLevelMapper;
import org.example.modules.member.service.MemberLevelService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:34:48
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:34:48
 * @Description 会员等级表(MemberLevel)表服务实现类
 */
@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevelEntity> implements MemberLevelService {

}

