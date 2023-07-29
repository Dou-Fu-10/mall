package org.example.modules.admin.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.admin.system.entity.PrizePoolEntity;

/**
 * Created by Dou-Fu-10 2023-07-29 15:47:03
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 15:47:03
 * @Description 奖金池(PrizePool)表数据库访问层
 */
@Mapper
public interface PrizePoolMapper extends BaseMapper<PrizePoolEntity> {

}

