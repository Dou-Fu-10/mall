package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.dto.MenuDto;
import org.example.modules.system.entity.vo.MenuVo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


/**
 * Created by Dou-Fu-10 2023-07-09 18:52:13
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:13
 * @Description 后台菜单表(Menu)表服务接口
 */
public interface MenuService extends IService<MenuEntity> {

    /**
     * 修改菜单显示状态
     *
     * @param id     菜单id
     * @param hidden 显示状态
     * @return 是否修改成功
     */
    Boolean updateHidden(Long id, Boolean hidden);

    /**
     * 根据当前用户获取菜单
     *
     * @param adminId 当前用户
     * @return 菜单
     */
    List<MenuVo> findByUser(Long adminId);

    /**
     * 查询所有的一级菜单
     *
     * @return 一级菜单列表
     */
    List<MenuVo> getOneLevelMenu();

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param menuDto 查询实体
     * @return 所有数据
     */
    Page<MenuVo> page(Page<MenuEntity> page, MenuDto menuDto);

    /**
     * 通过菜单id列表查询 菜单
     *
     * @param menusId 菜单id
     * @return 所有数据
     */
    List<MenuVo> findByMenusId(Set<Long> menusId);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    MenuVo getByMenuId(Serializable id);

    /**
     * 新增数据
     *
     * @param menuDto 实体对象
     * @return 新增结果
     */
    Boolean save(MenuDto menuDto);

    /**
     * 标题
     *
     * @param title 标题
     * @return 标题
     */
    MenuEntity getTitle(String title);
    /**
     * 修改数据
     *
     * @param menuDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(MenuDto menuDto);
}
