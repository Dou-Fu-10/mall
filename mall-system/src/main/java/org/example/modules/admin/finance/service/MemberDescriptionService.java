package org.example.modules.admin.finance.service;

import org.example.modules.admin.finance.entity.MemberDescriptionEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.admin.finance.entity.dto.MemberDescriptionDto;
import org.example.modules.admin.finance.entity.vo.MemberDescriptionVo;

/**
 * Created by Dou-Fu-10 2023-07-31 15:40:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:40:17
 * @Description 会员详细说明(MemberDescription)表服务接口
 */
public interface MemberDescriptionService extends IService<MemberDescriptionEntity> {
    /**
     * 新增数据
     *
     * @param memberDescriptionDto 实体对象
     * @return 新增结果
     */
    Boolean save(MemberDescriptionDto memberDescriptionDto);

    /**
     * 修改数据
     *
     * @param memberDescriptionDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(MemberDescriptionDto memberDescriptionDto);

    /**
     * 分页查询所有数据
     *
     * @param page                 分页对象
     * @param memberDescriptionDto 查询实体
     * @return 所有数据
     */
    Page<MemberDescriptionVo> page(Page<MemberDescriptionEntity> page, MemberDescriptionDto memberDescriptionDto);
}
