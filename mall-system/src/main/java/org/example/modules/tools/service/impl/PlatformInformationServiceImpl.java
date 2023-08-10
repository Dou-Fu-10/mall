package org.example.modules.tools.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.tools.entity.PlatformInformationEntity;
import org.example.modules.tools.entity.dto.PlatformInformationDto;
import org.example.modules.tools.entity.vo.PlatformInformationVo;
import org.example.modules.tools.mapper.PlatformInformationMapper;
import org.example.modules.tools.service.PlatformInformationService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-08-10 22:21:34
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 22:21:34
 * @Description 平台信息(PlatformInformation)表服务实现类
 */
@Service("platformInformationService")
public class PlatformInformationServiceImpl extends ServiceImpl<PlatformInformationMapper, PlatformInformationEntity> implements PlatformInformationService {
    @Override
    public Boolean save(PlatformInformationDto platformInformationDto) {
        PlatformInformationEntity platformInformationEntity = BeanCopy.convert(platformInformationDto, PlatformInformationEntity.class);
        return save(platformInformationEntity);
    }

    @Override
    public Boolean updateById(PlatformInformationDto platformInformationDto) {
        PlatformInformationEntity platformInformationEntity = BeanCopy.convert(platformInformationDto, PlatformInformationEntity.class);
        return updateById(platformInformationEntity);
    }

    @Override
    public Page<PlatformInformationVo> page(Page<PlatformInformationEntity> page, PlatformInformationDto platformInformationDto) {
        PlatformInformationEntity platformInformationEntity = BeanCopy.convert(platformInformationDto, PlatformInformationEntity.class);
        LambdaQueryWrapper<PlatformInformationEntity> platformInformationEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(platformInformationEntity);
        Page<PlatformInformationEntity> platformInformationEntityPage = page(page, platformInformationEntityLambdaQueryWrapper);
        IPage<PlatformInformationVo> platformInformationEntityPageVoIpage = platformInformationEntityPage.convert(platformInformation -> BeanCopy.convert(platformInformation, PlatformInformationVo.class));
        return (Page) platformInformationEntityPageVoIpage;
    }
}

