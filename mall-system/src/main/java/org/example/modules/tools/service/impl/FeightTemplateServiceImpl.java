package org.example.modules.tools.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.tools.entity.FeightTemplateEntity;
import org.example.modules.tools.mapper.FeightTemplateMapper;
import org.example.modules.tools.service.FeightTemplateService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:33:38
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:33:38
 * @Description 运费模版(FeightTemplate)表服务实现类
 */
@Service("feightTemplateService")
public class FeightTemplateServiceImpl extends ServiceImpl<FeightTemplateMapper, FeightTemplateEntity> implements FeightTemplateService {

}

