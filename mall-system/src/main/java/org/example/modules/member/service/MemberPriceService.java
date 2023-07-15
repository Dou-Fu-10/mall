package org.example.modules.member.service;

import org.example.modules.member.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.member.entity.dto.MemberPriceDto;
import org.example.modules.product.entity.dto.SkuStockDto;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-15 11:35:10
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:10
 * @Description 商品会员价格表(MemberPrice)表服务接口
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {
    /**
     * 新增数据
     *
     * @param memberPrice 实体对象
     * @return 新增结果
     */
    boolean save(MemberPriceDto memberPrice);

    /**
     * 新增数据
     *
     * @param memberPrice 实体对象
     * @return 新增结果
     */
    boolean save(List<MemberPriceDto> memberPrice);

    /**
     * 修改数据
     *
     * @param memberPrice 实体对象
     * @return 修改结果
     */
    boolean updateById(MemberPriceDto memberPrice);

    List<MemberPriceDto> getMemberPriceByProductId(Long productId);
}
