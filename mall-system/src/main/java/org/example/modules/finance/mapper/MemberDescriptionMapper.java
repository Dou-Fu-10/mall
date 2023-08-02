package org.example.modules.finance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.finance.entity.MemberDescriptionEntity;

/**
 * Created by Dou-Fu-10 2023-07-31 15:40:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:40:17
 * @Description 会员详细说明(MemberDescription)表数据库访问层
 */
@Mapper
public interface MemberDescriptionMapper extends BaseMapper<MemberDescriptionEntity> {

}

