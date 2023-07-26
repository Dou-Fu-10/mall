package org.example.modules.admin.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.member.entity.MemberEntity;
import org.example.modules.admin.member.entity.dto.MemberDto;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:16
 * @Description 会员表(Member)表服务接口
 */
public interface MemberService extends IService<MemberEntity> {
    /**
     * 新增数据
     *
     * @param Member 实体对象
     * @return 新增结果
     */
    boolean save(MemberDto Member);

    /**
     * 修改数据
     *
     * @param Member 实体对象
     * @return 修改结果
     */
    boolean updateById(MemberDto Member);
}
