package org.example.modules.tools.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.tools.entity.CompanyAddressEntity;
import org.example.modules.tools.entity.dto.CompanyAddressDto;
import org.example.modules.tools.entity.vo.CompanyAddressVo;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:02
 * @Description 公司收发货地址表(CompanyAddress)表服务接口
 */
public interface CompanyAddressService extends IService<CompanyAddressEntity> {
    /**
     * 新增数据
     *
     * @param companyAddress 实体对象
     * @return 新增结果
     */
    boolean save(CompanyAddressDto companyAddress);

    /**
     * 修改数据
     *
     * @param companyAddress 实体对象
     * @return 修改结果
     */
    boolean updateById(CompanyAddressDto companyAddress);
    /**
     * 通过主键查询单条数据
     *
     * @param companyAddressId 主键
     * @return 单条数据
     */
    CompanyAddressVo getByCompanyAddressIdId(Long companyAddressId);
}
