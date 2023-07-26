package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.vo.MenuVo;

import java.util.List;


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
    boolean updateHidden(Long id, Integer hidden);

    /**
     * 根据当前用户获取菜单
     *
     * @param currentUserId 当前用户
     * @return 菜单
     */
    List<MenuVo> findByUser(Long currentUserId);

    /**
     * 查询所有的一级菜单
     *
     * @return 一级菜单列表
     */
    List<MenuVo> getOneLevelMenu();

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param menu 查询实体
     * @return 所有数据
     */
    Page<MenuVo> page(Page<MenuEntity> page, MenuEntity menu);
}
