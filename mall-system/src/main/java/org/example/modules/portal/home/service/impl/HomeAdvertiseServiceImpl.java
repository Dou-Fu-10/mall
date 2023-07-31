package org.example.modules.portal.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.portal.home.entity.HomeAdvertiseEntity;
import org.example.modules.portal.home.entity.dto.HomeAdvertiseDto;
import org.example.modules.portal.home.entity.vo.HomeAdvertiseVo;
import org.example.modules.portal.home.mapper.HomeAdvertiseMapper;
import org.example.modules.portal.home.service.HomeAdvertiseService;

/**
 * Created by Dou-Fu-10 2023-07-31 16:16:51
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 16:16:51
 * @Description 首页轮播广告表(HomeAdvertise)表服务实现类
 */
@Service("homeAdvertiseService")
public class HomeAdvertiseServiceImpl extends ServiceImpl<HomeAdvertiseMapper, HomeAdvertiseEntity> implements HomeAdvertiseService {
    @Override
    public Boolean save(HomeAdvertiseDto homeAdvertiseDto) {
        HomeAdvertiseEntity homeAdvertiseEntity = BeanCopy.convert(homeAdvertiseDto, HomeAdvertiseEntity.class);
        return save(homeAdvertiseEntity);
    }

    @Override
    public Boolean updateById(HomeAdvertiseDto homeAdvertiseDto) {
        HomeAdvertiseEntity homeAdvertiseEntity = BeanCopy.convert(homeAdvertiseDto, HomeAdvertiseEntity.class);
        return updateById(homeAdvertiseEntity);
    }

    @Override
    public Page<HomeAdvertiseVo> page(Page<HomeAdvertiseEntity> page, HomeAdvertiseDto homeAdvertiseDto) {
        HomeAdvertiseEntity homeAdvertiseEntity = BeanCopy.convert(homeAdvertiseDto, HomeAdvertiseEntity.class);
        LambdaQueryWrapper<HomeAdvertiseEntity> homeAdvertiseEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(homeAdvertiseEntity);
        Page<HomeAdvertiseEntity> homeAdvertiseEntityPage = page(page, homeAdvertiseEntityLambdaQueryWrapper);
        IPage<HomeAdvertiseVo> homeAdvertiseEntityPageVoIpage = homeAdvertiseEntityPage.convert(homeAdvertise -> BeanCopy.convert(homeAdvertise, HomeAdvertiseVo.class));
        return (Page) homeAdvertiseEntityPageVoIpage;
    }
}

