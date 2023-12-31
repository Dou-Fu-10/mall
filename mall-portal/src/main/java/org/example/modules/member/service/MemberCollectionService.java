package org.example.modules.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.member.entity.MemberCollectionEntity;
import org.example.modules.member.entity.dto.MemberCollectionDto;
import org.example.modules.member.entity.vo.MemberCollectionVo;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员收藏(MemberCollection)表服务接口
 */
public interface MemberCollectionService extends IService<MemberCollectionEntity> {
    /**
     * 新增数据
     *
     * @param memberCollectionDto 实体对象
     * @return 新增结果
     */
    Boolean save(MemberCollectionDto memberCollectionDto);

    /**
     * 修改数据
     *
     * @param memberCollectionDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(MemberCollectionDto memberCollectionDto);

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    Page<MemberCollectionVo> page(Page<MemberCollectionEntity> page);

    /**
     * 商品是否已收藏
     *
     * @param productId 商品id
     * @return /
     */
    Boolean collectOrNot(Serializable productId);
}
