package org.example.modules.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.member.entity.MemberLoginLogEntity;
import org.example.modules.member.entity.dto.MemberLoginLogDto;

/**
 * Created by PanShiFu 2023-07-14 14:34:17
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:17
 * @Description 会员登录记录(MemberLoginLog)表服务接口
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {
    /**
     * 新增数据
     *
     * @param MemberLoginLog 实体对象
     * @return 新增结果
     */
    boolean save(MemberLoginLogDto MemberLoginLog);

    /**
     * 修改数据
     *
     * @param MemberLoginLog 实体对象
     * @return 修改结果
     */
    boolean updateById(MemberLoginLogDto MemberLoginLog);
}
