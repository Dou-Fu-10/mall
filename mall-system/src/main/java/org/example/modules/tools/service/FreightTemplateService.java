package org.example.modules.tools.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.tools.entity.FreightTemplateEntity;
import org.example.modules.tools.entity.dto.FreightTemplateDto;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:26
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:26
 * @Description 运费模版(FreightTemplate)表服务接口
 */
public interface FreightTemplateService extends IService<FreightTemplateEntity> {
    /**
     * 新增数据
     *
     * @param FreightTemplate 实体对象
     * @return 新增结果
     */
    boolean save(FreightTemplateDto FreightTemplate);

    /**
     * 修改数据
     *
     * @param FreightTemplate 实体对象
     * @return 修改结果
     */
    boolean updateById(FreightTemplateDto FreightTemplate);
}
