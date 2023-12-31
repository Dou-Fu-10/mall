package org.example.modules.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.finance.entity.PrizePoolEntity;
import org.example.modules.finance.entity.dto.PrizePoolDto;
import org.example.modules.finance.entity.vo.PrizePoolVo;
import org.example.modules.finance.entity.vo.PrizeVo;

import java.math.BigDecimal;

/**
 * Created by Dou-Fu-10 2023-07-29 16:07:50
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 16:07:50
 * @Description 奖金池(PrizePool)表服务接口
 */
public interface PrizePoolService extends IService<PrizePoolEntity> {
    /**
     * 新增数据
     *
     * @param prizePool 实体对象
     * @return 新增结果
     */
//    Boolean save(PrizePoolDto prizePool);

    /**
     * 修改数据
     *
     * @param prizePool 实体对象
     * @return 修改结果
     */
    Boolean updateById(PrizePoolDto prizePool);

    /**
     * 查询奖金池金额
     *
     * @return 金额
     */
    PrizeVo details();

    /**
     * 查询会员费用
     *
     * @return 金额
     */
    BigDecimal getMemberFees();

    /**
     * 查询单月奖金池数据
     *
     * @return 所有数据
     */
    PrizePoolVo select();

    /**
     * 奖金池收益计算
     *
     * @return /
     */
    Boolean IncomeCalculation();
}
