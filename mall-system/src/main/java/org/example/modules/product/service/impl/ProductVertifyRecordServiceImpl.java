package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.product.entity.ProductVertifyRecordEntity;
import org.example.modules.product.mapper.ProductVertifyRecordMapper;
import org.example.modules.product.service.ProductVertifyRecordService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:57
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:57
 * @Description 商品审核记录(ProductVertifyRecord)表服务实现类
 */
@Service("productVertifyRecordService")
public class ProductVertifyRecordServiceImpl extends ServiceImpl<ProductVertifyRecordMapper, ProductVertifyRecordEntity> implements ProductVertifyRecordService {

}

