package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.vo.MemberPriceVo;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.entity.vo.SkuStockVo;
import org.springframework.stereotype.Service;
import org.example.modules.member.entity.MemberPriceEntity;
import org.example.modules.member.entity.dto.MemberPriceDto;
import org.example.modules.member.mapper.MemberPriceMapper;
import org.example.modules.member.service.MemberPriceService;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-15 11:35:10
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:10
 * @Description 商品会员价格表(MemberPrice)表服务实现类
 */
@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceMapper, MemberPriceEntity> implements MemberPriceService {
    @Override
    public Boolean save(MemberPriceDto memberPrice) {
        return false;
    }

    @Override
    public Boolean save(List<MemberPriceDto> memberPrice) {
        List<MemberPriceEntity> memberPriceEntityList = BeanCopy.copytList(memberPrice, MemberPriceEntity.class);
        // TODO 数据校验
        return saveBatch(memberPriceEntityList);
    }

    @Override
    public Boolean updateById(MemberPriceDto memberPrice) {
        return false;
    }

    @Override
    public List<MemberPriceVo> getMemberPriceByProductId(Long productId) {
        List<MemberPriceEntity> memberPriceEntityList = lambdaQuery().ge(MemberPriceEntity::getProductId, productId).list();
        return BeanCopy.copytList(memberPriceEntityList, MemberPriceVo.class);
    }

    @Override
    public Boolean saveOrUpdate(List<MemberPriceDto> memberPrice) {
        return null;
    }
}
