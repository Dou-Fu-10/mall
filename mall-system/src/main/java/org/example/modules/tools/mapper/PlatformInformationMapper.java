package org.example.modules.tools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.tools.entity.PlatformInformationEntity;

/**
 * Created by Dou-Fu-10 2023-08-10 22:21:34
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 22:21:34
 * @Description 平台信息(PlatformInformation)表数据库访问层
 */
@Mapper
public interface PlatformInformationMapper extends BaseMapper<PlatformInformationEntity> {

}

