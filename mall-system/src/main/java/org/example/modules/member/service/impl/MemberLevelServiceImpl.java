package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.member.entity.MemberLevelEntity;
import org.example.modules.member.entity.dto.MemberLevelDto;
import org.example.modules.member.mapper.MemberLevelMapper;
import org.example.modules.member.service.MemberLevelService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-14 14:34:17
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:17
 * @Description 会员等级表(MemberLevel)表服务实现类
 */
@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevelEntity> implements MemberLevelService {
    @Override
    public boolean save(MemberLevelDto MemberLevel) {
        return false;
    }

    @Override
    public boolean updateById(MemberLevelDto MemberLevel) {
        return false;
    }
}

