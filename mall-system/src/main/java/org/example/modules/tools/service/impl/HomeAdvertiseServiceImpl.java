package org.example.modules.tools.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.server.MinioServer;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.service.ProductService;
import org.example.modules.tools.entity.HomeAdvertiseEntity;
import org.example.modules.tools.entity.dto.HomeAdvertiseDto;
import org.example.modules.tools.entity.vo.HomeAdvertiseVo;
import org.example.modules.tools.mapper.HomeAdvertiseMapper;
import org.example.modules.tools.service.HomeAdvertiseService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-08-10 22:21:00
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 22:21:00
 * @Description 首页轮播广告表(HomeAdvertise)表服务实现类
 */
@Service("homeAdvertiseService")
public class HomeAdvertiseServiceImpl extends ServiceImpl<HomeAdvertiseMapper, HomeAdvertiseEntity> implements HomeAdvertiseService {
    @Resource
    private MinioServer minioServer;
    @Resource
    private ProductService productService;

    private HomeAdvertiseEntity getByHomeAdvertiseName(String name) {
        return lambdaQuery().eq(HomeAdvertiseEntity::getName, name).one();
    }

    @Override
    public Boolean save(@NotNull HomeAdvertiseDto homeAdvertiseDto) {
        String url = homeAdvertiseDto.getPic();
        if (!minioServer.checkObjectIsExist(url)) {
            throw new BaseRequestException("请输入正确的图片地址");
        }
        ProductVo productVo = productService.getByProductId(homeAdvertiseDto.getProductId());
        if (Objects.isNull(productVo)) {
            throw new BaseRequestException("请输入正确商品地址");
        }
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
        return (Page<HomeAdvertiseVo>) homeAdvertiseEntityPageVoIpage;
    }

    @Override
    public HomeAdvertiseVo getByHomeAdvertiseId(Serializable id) {
        HomeAdvertiseEntity homeAdvertiseEntity = getById(id);
        return BeanCopy.convert(homeAdvertiseEntity, HomeAdvertiseVo.class);
    }
}

