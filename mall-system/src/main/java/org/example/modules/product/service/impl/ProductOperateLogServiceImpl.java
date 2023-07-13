package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.product.entity.ProductOperateLogEntity;
import org.example.modules.product.mapper.ProductOperateLogMapper;
import org.example.modules.product.service.ProductOperateLogService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:56
 * @Description (ProductOperateLog)表服务实现类
 */
@Service("productOperateLogService")
public class ProductOperateLogServiceImpl extends ServiceImpl<ProductOperateLogMapper, ProductOperateLogEntity> implements ProductOperateLogService {

}

