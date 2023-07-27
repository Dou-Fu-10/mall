package org.example.modules.portal.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.portal.member.entity.MemberPriceEntity;
import org.example.modules.portal.member.entity.dto.MemberPriceDto;
import org.example.modules.portal.member.entity.vo.MemberPriceVo;

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
    Boolean save(MemberPriceDto memberPrice);

    /**
     * 新增数据
     *
     * @param memberPrice 实体对象
     * @return 新增结果
     */
    Boolean save(List<MemberPriceDto> memberPrice);

    /**
     * 修改数据
     *
     * @param memberPrice 实体对象
     * @return 修改结果
     */
    Boolean updateById(MemberPriceDto memberPrice);

    /**
     * 通过商品id 商品会员价格表
     *
     * @param productId 商品id
     * @return 会员价格
     */
    List<MemberPriceVo> getMemberPriceByProductId(Long productId);

    /**
     * 新增数据
     *
     * @param memberPrice 实体对象
     * @return 新增结果
     */
    Boolean saveOrUpdate(List<MemberPriceDto> memberPrice);
}
