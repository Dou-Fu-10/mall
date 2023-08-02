package org.example.modules.tools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.tools.entity.CompanyAddressEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:01
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:01
 * @Description 公司收发货地址表(CompanyAddress)表数据库访问层
 */
@Mapper
public interface CompanyAddressMapper extends BaseMapper<CompanyAddressEntity> {

}

