package org.example.modules.admin.tools.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.admin.tools.entity.FeightTemplateEntity;
import org.example.modules.admin.tools.entity.dto.FeightTemplateDto;
import org.example.modules.admin.tools.mapper.FeightTemplateMapper;
import org.example.modules.admin.tools.service.FeightTemplateService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:26
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:26
 * @Description 运费模版(FeightTemplate)表服务实现类
 */
@Service("feightTemplateService")
public class FeightTemplateServiceImpl extends ServiceImpl<FeightTemplateMapper, FeightTemplateEntity> implements FeightTemplateService {
    @Override
    public boolean save(FeightTemplateDto FeightTemplate) {
        return false;
    }

    @Override
    public boolean updateById(FeightTemplateDto FeightTemplate) {
        return false;
    }
}

