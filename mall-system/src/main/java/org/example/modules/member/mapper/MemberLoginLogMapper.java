package org.example.modules.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.member.entity.MemberLoginLogEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:17
 * @Description 会员登录记录(MemberLoginLog)表数据库访问层
 */
@Mapper
public interface MemberLoginLogMapper extends BaseMapper<MemberLoginLogEntity> {

}

