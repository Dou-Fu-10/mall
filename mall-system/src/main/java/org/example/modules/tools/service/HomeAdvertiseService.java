package org.example.modules.tools.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.tools.entity.HomeAdvertiseEntity;
import org.example.modules.tools.entity.dto.HomeAdvertiseDto;
import org.example.modules.tools.entity.vo.HomeAdvertiseVo;

/**
 * Created by Dou-Fu-10 2023-08-10 22:21:00
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 22:21:00
 * @Description 首页轮播广告表(HomeAdvertise)表服务接口
 */
public interface HomeAdvertiseService extends IService<HomeAdvertiseEntity> {
    /**
     * 新增数据
     *
     * @param homeAdvertiseDto 实体对象
     * @return 新增结果
     */
    Boolean save(HomeAdvertiseDto homeAdvertiseDto);

    /**
     * 修改数据
     *
     * @param homeAdvertiseDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(HomeAdvertiseDto homeAdvertiseDto);

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param homeAdvertiseDto 查询实体
     * @return 所有数据
     */
    Page<HomeAdvertiseVo> page(Page<HomeAdvertiseEntity> page, HomeAdvertiseDto homeAdvertiseDto);
}
