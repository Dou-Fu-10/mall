package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductOperateLogEntity;
import org.example.modules.product.entity.dto.ProductOperateLogDto;
import org.example.modules.product.mapper.ProductOperateLogMapper;
import org.example.modules.product.service.ProductOperateLogService;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:18
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:18
 * @Description 商品价格变动记录(ProductOperateLog)表服务实现类
 */
@Service("productOperateLogService")
public class ProductOperateLogServiceImpl extends ServiceImpl<ProductOperateLogMapper, ProductOperateLogEntity> implements ProductOperateLogService {
    @Override
    public boolean save(ProductOperateLogDto productOperateLog) {
        return false;
    }

    @Override
    public boolean updateById(ProductOperateLogDto productOperateLog) {
        return false;
    }
}

