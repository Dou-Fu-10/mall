package org.example.modules.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.system.entity.PrizePoolEntity;
import org.example.modules.admin.system.entity.dto.PrizePoolDto;
import org.example.modules.admin.system.entity.vo.PrizePoolVo;
import org.example.modules.admin.system.mapper.PrizePoolMapper;
import org.example.modules.admin.system.service.PrizePoolService;

/**
 * Created by Dou-Fu-10 2023-07-29 16:17:13
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 16:17:13
 * @Description 奖金池(PrizePool)表服务实现类
 */
@Service("prizePoolService")
public class PrizePoolServiceImpl extends ServiceImpl<PrizePoolMapper, PrizePoolEntity> implements PrizePoolService {
    @Override
    public Boolean save(PrizePoolDto prizePoolDto) {
        PrizePoolEntity prizePoolEntity = BeanCopy.convert(prizePoolDto, PrizePoolEntity.class);
        return save(prizePoolEntity);
    }

    @Override
    public Boolean updateById(PrizePoolDto prizePoolDto) {
        PrizePoolEntity prizePoolEntity = BeanCopy.convert(prizePoolDto, PrizePoolEntity.class);
        return updateById(prizePoolEntity);
    }

    @Override
    public Page<PrizePoolVo> page(Page<PrizePoolEntity> page, PrizePoolDto prizePoolDto) {
        PrizePoolEntity prizePoolEntity = BeanCopy.convert(prizePoolDto, PrizePoolEntity.class);
        LambdaQueryWrapper<PrizePoolEntity> prizePoolEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(prizePoolEntity);
        Page<PrizePoolEntity> prizePoolEntityPage = page(page, prizePoolEntityLambdaQueryWrapper);
        IPage<PrizePoolVo> prizePoolEntityPageVoIpage = prizePoolEntityPage.convert(prizePool -> BeanCopy.convert(prizePool, PrizePoolVo.class));
        return (Page) prizePoolEntityPageVoIpage;
    }
}

