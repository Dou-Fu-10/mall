package org.example.modules.admin.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.member.entity.MemberLevelEntity;
import org.example.modules.admin.member.entity.dto.MemberLevelDto;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:17
 * @Description 会员等级表(MemberLevel)表服务接口
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {
    /**
     * 新增数据
     *
     * @param MemberLevel 实体对象
     * @return 新增结果
     */
    boolean save(MemberLevelDto MemberLevel);

    /**
     * 修改数据
     *
     * @param MemberLevel 实体对象
     * @return 修改结果
     */
    boolean updateById(MemberLevelDto MemberLevel);
}
