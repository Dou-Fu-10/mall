package org.example.modules.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.member.entity.MemberReferralCodeEntity;

/**
 * Created by Dou-Fu-10 2023-08-09 12:46:30
 *
 * @author Dou-Fu-10
 * @date 2023-08-09 12:46:30
 * @Description 推荐码(MemberReferralCode)表数据库访问层
 */
@Mapper
public interface MemberReferralCodeMapper extends BaseMapper<MemberReferralCodeEntity> {

}

