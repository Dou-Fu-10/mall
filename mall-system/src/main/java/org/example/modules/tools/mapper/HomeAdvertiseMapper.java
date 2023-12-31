package org.example.modules.tools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.tools.entity.HomeAdvertiseEntity;

/**
 * Created by Dou-Fu-10 2023-08-10 22:21:00
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 22:21:00
 * @Description 首页轮播广告表(HomeAdvertise)表数据库访问层
 */
@Mapper
public interface HomeAdvertiseMapper extends BaseMapper<HomeAdvertiseEntity> {

}

