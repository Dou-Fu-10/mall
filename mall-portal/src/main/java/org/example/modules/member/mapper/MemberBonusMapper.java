package org.example.modules.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.member.entity.MemberBonusEntity;

/**
 * 奖金池每天收益(MemberBonus)表数据库访问层
 * Created by Dou-Fu-10 2023-08-13 15:14:54
 *
 * @author Dou-Fu-10
 * @date 2023-08-13 15:14:54
 * @Description 奖金池每天收益(MemberBonus)表数据库访问层
 */
@Mapper
public interface MemberBonusMapper extends BaseMapper<MemberBonusEntity> {

}

