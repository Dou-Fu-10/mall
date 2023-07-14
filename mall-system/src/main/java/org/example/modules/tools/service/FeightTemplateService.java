package org.example.modules.tools.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.tools.entity.FeightTemplateEntity;
import org.example.modules.tools.entity.dto.FeightTemplateDto;

/**
 * Created by PanShiFu 2023-07-14 14:36:26
 *
 * @author PanShiFu
 * @date 2023-07-14 14:36:26
 * @Description 运费模版(FeightTemplate)表服务接口
 */
public interface FeightTemplateService extends IService<FeightTemplateEntity> {
    /**
     * 新增数据
     *
     * @param FeightTemplate 实体对象
     * @return 新增结果
     */
    boolean save(FeightTemplateDto FeightTemplate);

    /**
     * 修改数据
     *
     * @param FeightTemplate 实体对象
     * @return 修改结果
     */
    boolean updateById(FeightTemplateDto FeightTemplate);
}
