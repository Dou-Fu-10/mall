package org.example.modules.tools.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.tools.entity.CompanyAddressEntity;
import org.example.modules.tools.entity.dto.CompanyAddressDto;
import org.example.modules.tools.entity.vo.CompanyAddressVo;
import org.example.modules.tools.mapper.CompanyAddressMapper;
import org.example.modules.tools.service.CompanyAddressService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:02
 * @Description 公司收发货地址表(CompanyAddress)表服务实现类
 */
@Service("companyAddressService")
public class CompanyAddressServiceImpl extends ServiceImpl<CompanyAddressMapper, CompanyAddressEntity> implements CompanyAddressService {
    @Override
    public boolean save(CompanyAddressDto companyAddress) {
        return false;
    }

    @Override
    public boolean updateById(CompanyAddressDto companyAddress) {
        return false;
    }

    @Override
    public CompanyAddressVo getByCompanyAddressIdId(Long companyAddressId) {
        if (Objects.isNull(companyAddressId)) {
            throw new BaseRequestException("参数错误");
        }
        return BeanCopy.convert(getById(companyAddressId), CompanyAddressVo.class);
    }

    @Override
    public Page<CompanyAddressVo> page(Page<CompanyAddressEntity> page, CompanyAddressDto companyAddressDto) {
        CompanyAddressEntity companyAddressEntity = BeanCopy.convert(companyAddressDto, CompanyAddressEntity.class);
        Page<CompanyAddressEntity> companyAddressEntityPage = page(page, new QueryWrapper<>(companyAddressEntity));
        IPage<CompanyAddressVo> companyAddressVoIPage = companyAddressEntityPage.convert(companyAddress -> BeanCopy.convert(companyAddress, CompanyAddressVo.class));
        return (Page<CompanyAddressVo>) companyAddressVoIPage;
    }

    @Override
    public CompanyAddressVo getByCompanyAddressId(Serializable id) {
        CompanyAddressEntity companyAddressEntity = getById(id);
        return BeanCopy.convert(companyAddressEntity, CompanyAddressVo.class);
    }
}

