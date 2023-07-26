package org.example.modules.admin.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.product.entity.ProductVertifyRecordEntity;
import org.example.modules.admin.product.entity.dto.ProductVertifyRecordDto;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:18
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:18
 * @Description 商品审核记录(ProductVertifyRecord)表服务接口
 */
public interface ProductVertifyRecordService extends IService<ProductVertifyRecordEntity> {
    /**
     * 新增数据
     *
     * @param productVertifyRecord 实体对象
     * @return 新增结果
     */
    boolean save(ProductVertifyRecordDto productVertifyRecord);

    /**
     * 修改数据
     *
     * @param productVertifyRecord 实体对象
     * @return 修改结果
     */
    boolean updateById(ProductVertifyRecordDto productVertifyRecord);
}
