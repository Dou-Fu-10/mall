package org.example.modules.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.member.entity.MemberLevelEntity;

/**
 * Created by PanShiFu 2023-07-13 15:34:48
 *
 * @author PanShiFu
 * @date 2023-07-13 15:34:48
 * @Description 会员等级表(MemberLevel)表数据库访问层
 */
@Mapper
public interface MemberLevelMapper extends BaseMapper<MemberLevelEntity> {

}

