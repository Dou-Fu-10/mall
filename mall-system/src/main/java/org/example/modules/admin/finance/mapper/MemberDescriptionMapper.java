package org.example.modules.admin.finance.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.admin.finance.entity.MemberDescriptionEntity;

/**
 * Created by Dou-Fu-10 2023-07-31 15:32:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:32:47
 * @Description (MemberDescription)表数据库访问层
 */
@Mapper
public interface MemberDescriptionMapper extends BaseMapper<MemberDescriptionEntity> {

}

