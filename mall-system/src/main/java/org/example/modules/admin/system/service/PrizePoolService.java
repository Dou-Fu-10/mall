package org.example.modules.admin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.system.entity.PrizePoolEntity;
import org.example.modules.admin.system.entity.dto.PrizePoolDto;
import org.example.modules.admin.system.entity.vo.PrizePoolVo;
import org.example.modules.admin.system.entity.vo.PrizeVo;

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
    Boolean save(PrizePoolDto prizePool);

    /**
     * 修改数据
     *
     * @param prizePool 实体对象
     * @return 修改结果
     */
    Boolean updateById(PrizePoolDto prizePool);

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param prizePool 查询实体
     * @return 所有数据
     */
    Page<PrizePoolVo> page(Page<PrizePoolEntity> page, PrizePoolDto prizePool);

    /**
     * 查询奖金池金额
     *
     * @return 金额
     */
    PrizeVo select();
}
