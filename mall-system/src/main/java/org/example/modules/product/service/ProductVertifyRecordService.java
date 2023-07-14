package org.example.modules.product.service;

import org.example.modules.product.entity.ProductVertifyRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.dto.ProductVertifyRecordDto;

/**
 * Created by PanShiFu 2023-07-14 13:54:18
 *
 * @author PanShiFu
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
