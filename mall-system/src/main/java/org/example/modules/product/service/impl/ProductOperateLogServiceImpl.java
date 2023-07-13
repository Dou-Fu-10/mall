package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductOperateLogEntity;
import org.example.modules.product.mapper.ProductOperateLogMapper;
import org.example.modules.product.service.ProductOperateLogService;

/**
 * Created by PanShiFu 2023-07-13 15:35:56
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:56
 * @Description (ProductOperateLog)表服务实现类
 */
@Service("productOperateLogService")
public class ProductOperateLogServiceImpl extends ServiceImpl<ProductOperateLogMapper, ProductOperateLogEntity> implements ProductOperateLogService {

}

