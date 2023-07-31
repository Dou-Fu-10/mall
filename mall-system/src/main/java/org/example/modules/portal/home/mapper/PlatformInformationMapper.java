package org.example.modules.portal.home.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.portal.home.entity.PlatformInformationEntity;

/**
 * Created by Dou-Fu-10 2023-07-31 22:28:57
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 22:28:57
 * @Description 平台信息(PlatformInformation)表数据库访问层
 */
@Mapper
public interface PlatformInformationMapper extends BaseMapper<PlatformInformationEntity> {

}

