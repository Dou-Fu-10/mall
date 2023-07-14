package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductVertifyRecordEntity;
import org.example.modules.product.entity.dto.ProductVertifyRecordDto;
import org.example.modules.product.mapper.ProductVertifyRecordMapper;
import org.example.modules.product.service.ProductVertifyRecordService;

/**
 * Created by PanShiFu 2023-07-14 13:54:18
 *
 * @author PanShiFu
 * @date 2023-07-14 13:54:18
 * @Description 商品审核记录(ProductVertifyRecord)表服务实现类
 */
@Service("productVertifyRecordService")
public class ProductVertifyRecordServiceImpl extends ServiceImpl<ProductVertifyRecordMapper, ProductVertifyRecordEntity> implements ProductVertifyRecordService {
    @Override
    public boolean save(ProductVertifyRecordDto productVertifyRecord) {
        return false;
    }

    @Override
    public boolean updateById(ProductVertifyRecordDto productVertifyRecord) {
        return false;
    }
}

