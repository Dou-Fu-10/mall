package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.product.entity.ProductLadderEntity;
import org.example.modules.product.mapper.ProductLadderMapper;
import org.example.modules.product.service.ProductLadderService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:56
 * @Description 产品阶梯价格表(只针对同商品)(ProductLadder)表服务实现类
 */
@Service("productLadderService")
public class ProductLadderServiceImpl extends ServiceImpl<ProductLadderMapper, ProductLadderEntity> implements ProductLadderService {

}

