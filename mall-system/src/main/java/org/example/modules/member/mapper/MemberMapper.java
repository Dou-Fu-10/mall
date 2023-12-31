package org.example.modules.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.common.core.entity.MemberEntity;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:04
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:04
 * @Description 会员表(Member)表数据库访问层
 */
@Mapper
public interface MemberMapper extends BaseMapper<MemberEntity> {

}

