package org.example.modules.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.member.entity.MemberLoginLogEntity;

/**
 * Created by PanShiFu 2023-07-14 14:34:17
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:17
 * @Description 会员登录记录(MemberLoginLog)表数据库访问层
 */
@Mapper
public interface MemberLoginLogMapper extends BaseMapper<MemberLoginLogEntity> {

}

