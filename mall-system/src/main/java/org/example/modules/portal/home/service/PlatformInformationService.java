package org.example.modules.portal.home.service;

import org.example.modules.portal.home.entity.PlatformInformationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.portal.home.entity.dto.PlatformInformationDto;
import org.example.modules.portal.home.entity.vo.PlatformInformationVo;

/**
 * Created by Dou-Fu-10 2023-07-31 22:28:57
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 22:28:57
 * @Description 平台信息(PlatformInformation)表服务接口
 */
public interface PlatformInformationService extends IService<PlatformInformationEntity> {
    /**
     * 新增数据
     *
     * @param platformInformationDto 实体对象
     * @return 新增结果
     */
    Boolean save(PlatformInformationDto platformInformationDto);

    /**
     * 修改数据
     *
     * @param platformInformationDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(PlatformInformationDto platformInformationDto);

    /**
     * 分页查询所有数据
     *
     * @param page                   分页对象
     * @param platformInformationDto 查询实体
     * @return 所有数据
     */
    Page<PlatformInformationVo> page(Page<PlatformInformationEntity> page, PlatformInformationDto platformInformationDto);
}
