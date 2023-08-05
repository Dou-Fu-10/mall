package org.example.modules.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.member.entity.MemberReadHistoryEntity;
import org.example.modules.member.entity.dto.MemberReadHistoryDto;
import org.example.modules.member.entity.vo.MemberReadHistoryVo;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员阅读历史记录(MemberReadHistory)表服务接口
 */
public interface MemberReadHistoryService extends IService<MemberReadHistoryEntity> {
    /**
     * 新增数据
     *
     * @param memberReadHistoryDto 实体对象
     * @return 新增结果
     */
    Boolean save(MemberReadHistoryDto memberReadHistoryDto);

    /**
     * 修改数据
     *
     * @param memberReadHistoryDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(MemberReadHistoryDto memberReadHistoryDto);

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    Page<MemberReadHistoryVo> page(Page<MemberReadHistoryEntity> page);
}
