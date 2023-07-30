package org.example.modules.admin.system.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.order.service.OrderService;
import org.example.modules.admin.system.entity.PrizePoolEntity;
import org.example.modules.admin.system.entity.dto.PrizePoolDto;
import org.example.modules.admin.system.entity.vo.PrizePoolVo;
import org.example.modules.admin.system.entity.vo.PrizeVo;
import org.example.modules.admin.system.mapper.PrizePoolMapper;
import org.example.modules.admin.system.service.PrizePoolService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Dou-Fu-10 2023-07-29 16:17:13
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 16:17:13
 * @Description 奖金池(PrizePool)表服务实现类
 */
@Service("prizePoolService")
public class PrizePoolServiceImpl extends ServiceImpl<PrizePoolMapper, PrizePoolEntity> implements PrizePoolService {

    @Resource
    private OrderService orderService;

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

    @Override
    public PrizeVo details() {
        // 按月查找 订单已完成的金额
        BigDecimal totalAmountCompletedOrder = orderService.findTotalAmountCompletedOrdersByMonth(new DateTime());

        LambdaQueryWrapper<PrizePoolEntity> prizePoolEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 获取月份的第一天
        DateTime firstDayOfMonth = DateUtil.beginOfMonth(DateUtil.date());
        // 获取月份的最后一天
        DateTime lastDayOfMonth = DateUtil.endOfMonth(DateUtil.date());
        // 已完成的订单
        prizePoolEntityLambdaQueryWrapper.between(PrizePoolEntity::getCreateTime, firstDayOfMonth, lastDayOfMonth);
        // 获取 奖金池
        PrizePoolEntity prizePoolEntity = getOne(prizePoolEntityLambdaQueryWrapper);
        // 获取 商品奖金池百分比
        BigDecimal bigDecimal = new BigDecimal(prizePoolEntity.getProductBonusesPercentage());
        BigDecimal productBonusesPercentageBigDecimal = bigDecimal.divide(new BigDecimal(100),2, RoundingMode.DOWN);
        // 获取 会员奖金池百分比
        BigDecimal bigDecimal1 = new BigDecimal(prizePoolEntity.getMemberBonusesPercentage());
        BigDecimal memberBonusesPercentageBigDecimal = bigDecimal1.divide(new BigDecimal(100),2, RoundingMode.DOWN);


        // 当月订单总金额 进行百分比计算 获取到商品奖金池
        BigDecimal commodityBonus = totalAmountCompletedOrder.multiply(productBonusesPercentageBigDecimal);
        // 当月商品奖金池 进行百分比计算 获取到会员奖金池
        BigDecimal memberBonus = commodityBonus.multiply(memberBonusesPercentageBigDecimal);
        return new PrizeVo(memberBonus, commodityBonus);
    }
}

