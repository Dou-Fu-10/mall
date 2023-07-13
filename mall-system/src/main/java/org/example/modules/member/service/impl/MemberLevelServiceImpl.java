package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.member.entity.MemberLevelEntity;
import org.example.modules.member.mapper.MemberLevelMapper;
import org.example.modules.member.service.MemberLevelService;

/**
 * Created by PanShiFu 2023-07-13 14:28:44
 *
 * @author PanShiFu
 * @date 2023-07-13 14:28:44
 * @Description 会员等级表(MemberLevel)表服务实现类
 */
@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevelEntity> implements MemberLevelService {

}

