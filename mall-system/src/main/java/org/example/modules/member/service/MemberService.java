package org.example.modules.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.member.entity.MemberEntity;
import org.example.modules.member.entity.dto.MemberDto;

/**
 * Created by PanShiFu 2023-07-14 14:34:16
 *
 * @author PanShiFu
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
