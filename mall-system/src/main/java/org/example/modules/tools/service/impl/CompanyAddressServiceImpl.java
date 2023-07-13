package org.example.modules.tools.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.tools.entity.CompanyAddressEntity;
import org.example.modules.tools.mapper.CompanyAddressMapper;
import org.example.modules.tools.service.CompanyAddressService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:33:00
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:33:00
 * @Description 公司收发货地址表(CompanyAddress)表服务实现类
 */
@Service("companyAddressService")
public class CompanyAddressServiceImpl extends ServiceImpl<CompanyAddressMapper, CompanyAddressEntity> implements CompanyAddressService {

}
