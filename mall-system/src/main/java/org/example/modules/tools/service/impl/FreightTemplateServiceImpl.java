package org.example.modules.tools.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.tools.entity.FreightTemplateEntity;
import org.example.modules.tools.entity.dto.FreightTemplateDto;
import org.example.modules.tools.mapper.FreightTemplateMapper;
import org.example.modules.tools.service.FreightTemplateService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:26
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:26
 * @Description 运费模版(FreightTemplate)表服务实现类
 */
@Service("freightTemplateService")
public class FreightTemplateServiceImpl extends ServiceImpl<FreightTemplateMapper, FreightTemplateEntity> implements FreightTemplateService {
    @Override
    public boolean save(FreightTemplateDto FreightTemplate) {
        return false;
    }

    @Override
    public boolean updateById(FreightTemplateDto FreightTemplate) {
        return false;
    }
}

