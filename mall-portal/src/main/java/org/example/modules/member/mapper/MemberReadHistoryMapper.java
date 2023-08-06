package org.example.modules.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.member.entity.MemberReadHistoryEntity;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员阅读历史记录(MemberReadHistory)表数据库访问层
 */
@Mapper
public interface MemberReadHistoryMapper extends BaseMapper<MemberReadHistoryEntity> {

}

