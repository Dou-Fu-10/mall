package org.example.modules.finance.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.finance.entity.MemberDescriptionEntity;
import org.example.modules.finance.entity.dto.MemberDescriptionDto;
import org.example.modules.finance.entity.vo.MemberDescriptionVo;

/**
 * Created by Dou-Fu-10 2023-07-31 15:40:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:40:17
 * @Description 会员详细说明(MemberDescription)表服务接口
 */
public interface MemberDescriptionService extends IService<MemberDescriptionEntity> {
    /**
     * 通过标题获取
     *
     * @param title 标题
     * @return /
     */
    MemberDescriptionEntity getByTitle(String title);

    /**
     * 新增数据
     *
     * @param memberDescriptionEntity 实体对象
     * @return 新增结果
     */
    Boolean insert(MemberDescriptionEntity memberDescriptionEntity);


    /**
     * 分页查询所有数据
     *
     * @param page                 分页对象
     * @param memberDescriptionDto 查询实体
     * @return 所有数据
     */
    Page<MemberDescriptionVo> page(Page<MemberDescriptionEntity> page, MemberDescriptionDto memberDescriptionDto);

    /**
     * 修改数据
     *
     * @param memberDescriptionEntity 实体对象
     * @return 修改结果
     */
    Boolean updateByMemberDescriptionId(MemberDescriptionEntity memberDescriptionEntity);
}
