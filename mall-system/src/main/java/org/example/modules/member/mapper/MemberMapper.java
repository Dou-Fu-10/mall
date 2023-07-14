package org.example.modules.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.member.entity.MemberEntity;

/**
 * Created by PanShiFu 2023-07-14 14:34:16
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:16
 * @Description 会员表(Member)表数据库访问层
 */
@Mapper
public interface MemberMapper extends BaseMapper<MemberEntity> {

}

