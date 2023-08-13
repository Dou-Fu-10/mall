package org.example.modules.member.service;

import org.example.modules.member.entity.MemberBonusEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * 奖金池每天收益(MemberBonus)表服务接口
 * Created by Dou-Fu-10 2023-08-13 15:09:49
 *
 * @author Dou-Fu-10
 * @date 2023-08-13 15:09:49
 * @Description 奖金池每天收益(MemberBonus)表服务接口
 */
public interface MemberBonusService extends IService<MemberBonusEntity> {
    /**
     * 新增数据
     *
     * @param bonus 实体对象
     * @return 新增结果
     */
    Boolean save(BigDecimal bonus);
}
