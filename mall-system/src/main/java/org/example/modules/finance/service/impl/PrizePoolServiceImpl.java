package org.example.modules.finance.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.finance.entity.PrizePoolEntity;
import org.example.modules.finance.entity.dto.PrizePoolDto;
import org.example.modules.finance.entity.vo.PrizePoolVo;
import org.example.modules.finance.entity.vo.PrizeVo;
import org.example.modules.finance.mapper.PrizePoolMapper;
import org.example.modules.finance.service.PrizePoolService;
import org.example.modules.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-29 16:17:13
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 16:17:13
 * @Description 奖金池(PrizePool)表服务实现类
 */
@Slf4j
@Service("prizePoolService")
public class PrizePoolServiceImpl extends ServiceImpl<PrizePoolMapper, PrizePoolEntity> implements PrizePoolService {

    @Resource
    private OrderService orderService;


    @Override
    public Boolean updateById(PrizePoolDto prizePoolDto) {
        PrizePoolEntity prizePoolEntity = BeanCopy.convert(prizePoolDto, PrizePoolEntity.class);
        return updateById(prizePoolEntity);
    }

    @Override
    public PrizePoolVo select() {
        // 获取月份的第一天
        DateTime firstDayOfMonth = DateUtil.beginOfMonth(DateUtil.date());
        log.info("第一天：" + firstDayOfMonth);
        // 获取月份的最后一天
        DateTime lastDayOfMonth = DateUtil.endOfMonth(DateUtil.date());
        log.info("最后一天：" + lastDayOfMonth);
        PrizePoolEntity prizePoolEntity = lambdaQuery().between(PrizePoolEntity::getCreateTime, firstDayOfMonth, lastDayOfMonth).one();
        // 如果为空说明这个月还没统计 奖金池
        if (Objects.isNull(prizePoolEntity)) {
            // 创建奖金池
            // 获取 最近月份的
            List<PrizePoolEntity> prizePoolEntities = lambdaQuery().orderByDesc(PrizePoolEntity::getCreateTime).list();
            if (CollectionUtils.isEmpty(prizePoolEntities)) {
                throw new BaseRequestException("请联系管理员");
            }
            // 获取距离最近的时间
            prizePoolEntity = prizePoolEntities.get(0);
            prizePoolEntity.setId(null);
            // 键id 制空 和 最近的时间添加
            prizePoolEntity.setCreateTime(new Date());
            prizePoolEntity.insert();
        }
        // 如果有 说明这个月已经统计了
        return BeanCopy.convert(prizePoolEntity, PrizePoolVo.class);
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
        if (Objects.isNull(prizePoolEntity)){
            throw new BaseRequestException("请联系管理员");
        }
        // 获取 商品奖金池百分比
        BigDecimal bigDecimal = new BigDecimal(prizePoolEntity.getProductBonusesPercentage());
        BigDecimal productBonusesPercentageBigDecimal = bigDecimal.divide(new BigDecimal(100), 2, RoundingMode.DOWN);
        // 获取 会员奖金池百分比
        BigDecimal bigDecimal1 = new BigDecimal(prizePoolEntity.getMemberBonusesPercentage());
        BigDecimal memberBonusesPercentageBigDecimal = bigDecimal1.divide(new BigDecimal(100), 2, RoundingMode.DOWN);


        // 当月订单总金额 进行百分比计算 获取到商品奖金池
        BigDecimal commodityBonus = totalAmountCompletedOrder.multiply(productBonusesPercentageBigDecimal);
        // 当月商品奖金池 进行百分比计算 获取到会员奖金池
        BigDecimal memberBonus = commodityBonus.multiply(memberBonusesPercentageBigDecimal);
        return new PrizeVo(memberBonus, commodityBonus);
    }

    @Override
    public BigDecimal getMemberFees() {
        PrizePoolEntity memberFees = lambdaQuery().select(PrizePoolEntity::getMemberFees).one();
        return memberFees.getMemberFees();
    }
}

