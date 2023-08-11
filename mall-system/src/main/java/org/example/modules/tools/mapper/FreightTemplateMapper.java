package org.example.modules.tools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.tools.entity.FreightTemplateEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:26
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:26
 * @Description 运费模版(FreightTemplate)表数据库访问层
 */
@Mapper
public interface FreightTemplateMapper extends BaseMapper<FreightTemplateEntity> {

}

