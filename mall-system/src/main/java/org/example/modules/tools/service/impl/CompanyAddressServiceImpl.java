package org.example.modules.tools.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.tools.entity.CompanyAddressEntity;
import org.example.modules.tools.entity.dto.CompanyAddressDto;
import org.example.modules.tools.mapper.CompanyAddressMapper;
import org.example.modules.tools.service.CompanyAddressService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-14 14:36:02
 *
 * @author PanShiFu
 * @date 2023-07-14 14:36:02
 * @Description 公司收发货地址表(CompanyAddress)表服务实现类
 */
@Service("companyAddressService")
public class CompanyAddressServiceImpl extends ServiceImpl<CompanyAddressMapper, CompanyAddressEntity> implements CompanyAddressService {
    @Override
    public boolean save(CompanyAddressDto CompanyAddress) {
        return false;
    }

    @Override
    public boolean updateById(CompanyAddressDto CompanyAddress) {
        return false;
    }
}

