package org.example.modules.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.member.entity.MemberEntity;

/**
 * Created by PanShiFu 2023-07-13 14:28:43
 *
 * @author PanShiFu
 * @date 2023-07-13 14:28:43
 * @Description 会员表(Member)表数据库访问层
 */
@Mapper
public interface MemberMapper extends BaseMapper<MemberEntity> {

}

