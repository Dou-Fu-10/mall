package org.example.modules.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.member.entity.MemberBonusEntity;
import org.example.modules.member.entity.vo.MemberBonusVo;

/**
 * 奖金池每天收益(MemberBonus)表服务接口
 * Created by Dou-Fu-10 2023-08-13 15:14:54
 *
 * @author Dou-Fu-10
 * @date 2023-08-13 15:14:54
 * @Description 奖金池每天收益(MemberBonus)表服务接口
 */
public interface MemberBonusService extends IService<MemberBonusEntity> {


    /**
     * 获取奖金池每天收益
     *
     * @return 获取奖金池每天收益
     */
    MemberBonusVo getMemberBonus();
}
