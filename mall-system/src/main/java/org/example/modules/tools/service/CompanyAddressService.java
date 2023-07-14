package org.example.modules.tools.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.tools.entity.CompanyAddressEntity;
import org.example.modules.tools.entity.dto.CompanyAddressDto;

/**
 * Created by PanShiFu 2023-07-14 14:36:02
 *
 * @author PanShiFu
 * @date 2023-07-14 14:36:02
 * @Description 公司收发货地址表(CompanyAddress)表服务接口
 */
public interface CompanyAddressService extends IService<CompanyAddressEntity> {
    /**
     * 新增数据
     *
     * @param CompanyAddress 实体对象
     * @return 新增结果
     */
    boolean save(CompanyAddressDto CompanyAddress);

    /**
     * 修改数据
     *
     * @param CompanyAddress 实体对象
     * @return 修改结果
     */
    boolean updateById(CompanyAddressDto CompanyAddress);
}
