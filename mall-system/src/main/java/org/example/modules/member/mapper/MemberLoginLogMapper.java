package org.example.modules.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.member.entity.MemberLoginLogEntity;

/**
 * Created by PanShiFu 2023-07-13 14:28:44
 *
 * @author PanShiFu
 * @date 2023-07-13 14:28:44
 * @Description 会员登录记录(MemberLoginLog)表数据库访问层
 */
@Mapper
public interface MemberLoginLogMapper extends BaseMapper<MemberLoginLogEntity> {

}

