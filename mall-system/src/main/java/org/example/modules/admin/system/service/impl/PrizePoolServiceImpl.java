package org.example.modules.admin.system.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.order.entity.vo.OrderVo;
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
import java.util.List;

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
    public PrizeVo select() {
        // 获取当前日期时间
        DateTime currentTime = DateUtil.date();
        List<OrderVo> orderVoList = orderService.findCompletedOrdersByMonth(currentTime);
        if (CollectionUtils.isEmpty(orderVoList)) {
            throw new BaseRequestException("当月没有完成的订单");
        }
        BigDecimal totalAmountCompletedOrder = BigDecimal.ZERO;
        // 计算当月订单总金额
        for (OrderVo orderVo : orderVoList) {
            BigDecimal payAmount = orderVo.getPayAmount();
            totalAmountCompletedOrder = totalAmountCompletedOrder.add(payAmount);
        }
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
        Integer productBonusesPercentage = prizePoolEntity.getProductBonusesPercentage();
        BigDecimal bigDecimal = new BigDecimal(productBonusesPercentage);
        // 当月订单总金额 进行百分比计算 获取到商品奖金池
        BigDecimal commodityBonus = totalAmountCompletedOrder.divide(bigDecimal, RoundingMode.DOWN);
        // 当月商品奖金池 进行百分比计算 获取到会员奖金池
        BigDecimal memberBonus = commodityBonus.divide(prizePoolEntity.getMemberFees(), RoundingMode.DOWN);
        return new PrizeVo(orderVoList, memberBonus, commodityBonus);
    }
}

