package org.example.modules.portal.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.portal.member.entity.MemberLoginLogEntity;
import org.example.modules.portal.member.entity.dto.MemberLoginLogDto;
import org.example.modules.portal.member.entity.vo.MemberLoginLogVo;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员登录记录(MemberLoginLog)表服务接口
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {
    /**
     * 新增数据
     *
     * @param memberLoginLogDto 实体对象
     * @return 新增结果
     */
    Boolean save(MemberLoginLogDto memberLoginLogDto);

    /**
     * 修改数据
     *
     * @param memberLoginLogDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(MemberLoginLogDto memberLoginLogDto);

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param memberLoginLogDto 查询实体
     * @return 所有数据
     */
    Page<MemberLoginLogVo> page(Page<MemberLoginLogEntity> page, MemberLoginLogDto memberLoginLogDto);
}
