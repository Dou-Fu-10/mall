package org.example.modules.tools.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.tools.entity.FeightTemplateEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 15:33:38
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:33:38
 * @Description 运费模版(FeightTemplate)表数据库访问层
 */
@Mapper
public interface FeightTemplateMapper extends BaseMapper<FeightTemplateEntity> {

}

