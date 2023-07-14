package org.example.modules.product.service;

import org.example.modules.product.entity.ProductOperateLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.dto.ProductOperateLogDto;

/**
 * Created by PanShiFu 2023-07-14 13:54:18
 *
 * @author PanShiFu
 * @date 2023-07-14 13:54:18
 * @Description 商品价格变动记录(ProductOperateLog)表服务接口
 */
public interface ProductOperateLogService extends IService<ProductOperateLogEntity> {
    /**
     * 新增数据
     *
     * @param productOperateLog 实体对象
     * @return 新增结果
     */
    boolean save(ProductOperateLogDto productOperateLog);

    /**
     * 修改数据
     *
     * @param productOperateLog 实体对象
     * @return 修改结果
     */
    boolean updateById(ProductOperateLogDto productOperateLog);
}
