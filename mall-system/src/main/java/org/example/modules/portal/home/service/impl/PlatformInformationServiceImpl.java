package org.example.modules.portal.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.portal.home.entity.PlatformInformationEntity;
import org.example.modules.portal.home.entity.dto.PlatformInformationDto;
import org.example.modules.portal.home.entity.vo.PlatformInformationVo;
import org.example.modules.portal.home.mapper.PlatformInformationMapper;
import org.example.modules.portal.home.service.PlatformInformationService;

/**
 * Created by Dou-Fu-10 2023-07-31 22:28:57
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 22:28:57
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

