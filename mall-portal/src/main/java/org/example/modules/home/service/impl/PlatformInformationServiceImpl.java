package org.example.modules.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.server.MinioServer;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.home.entity.PlatformInformationEntity;
import org.example.modules.home.entity.dto.PlatformInformationDto;
import org.example.modules.home.entity.vo.PlatformInformationVo;
import org.example.modules.home.mapper.PlatformInformationMapper;
import org.example.modules.home.service.PlatformInformationService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-31 22:28:57
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 22:28:57
 * @Description 平台信息(PlatformInformation)表服务实现类
 */
@Service("platformInformationService")
public class PlatformInformationServiceImpl extends ServiceImpl<PlatformInformationMapper, PlatformInformationEntity> implements PlatformInformationService {
    @Resource
    private MinioServer minioServer;

    @Override
    public Boolean save(PlatformInformationDto platformInformationDto) {
        PlatformInformationEntity platformInformationEntity = BeanCopy.convert(platformInformationDto, PlatformInformationEntity.class);
        Set<String> startupDiagram = platformInformationDto.getStartupDiagram();
        String albumPicStr = checkStartupDiagram(startupDiagram);
        if (Objects.nonNull(albumPicStr)) {
            platformInformationEntity.setStartupDiagram(albumPicStr);
        }
        return save(platformInformationEntity);
    }

    @Override
    public Boolean updateById(PlatformInformationDto platformInformationDto) {
        PlatformInformationEntity platformInformationEntity = BeanCopy.convert(platformInformationDto, PlatformInformationEntity.class);
        Set<String> startupDiagram = platformInformationDto.getStartupDiagram();
        String albumPicStr = checkStartupDiagram(startupDiagram);
        if (Objects.nonNull(albumPicStr)) {
            platformInformationEntity.setStartupDiagram(albumPicStr);
        }
        return updateById(platformInformationEntity);
    }

    @Override
    public Page<PlatformInformationVo> page(Page<PlatformInformationEntity> page, PlatformInformationDto platformInformationDto) {
        PlatformInformationEntity platformInformationEntity = BeanCopy.convert(platformInformationDto, PlatformInformationEntity.class);
        LambdaQueryWrapper<PlatformInformationEntity> platformInformationEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(platformInformationEntity);
        Page<PlatformInformationEntity> platformInformationEntityPage = page(page, platformInformationEntityLambdaQueryWrapper);
        IPage<PlatformInformationVo> platformInformationEntityPageVoIpage = platformInformationEntityPage.convert(platformInformation -> BeanCopy.convert(platformInformation, PlatformInformationVo.class));
        return (Page<PlatformInformationVo>) platformInformationEntityPageVoIpage;
    }

    private String checkStartupDiagram(Set<String> startupDiagram) {
        if (Objects.nonNull(startupDiagram) && CollectionUtils.isNotEmpty(startupDiagram)) {
            Set<String> existObject = minioServer.checkObjectIsExist(startupDiagram);
            if (!existObject.isEmpty()) {
                return String.join(",", existObject);
            }
        }
        return null;
    }
}

