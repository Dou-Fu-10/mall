package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.vo.MenuVo;

import java.awt.*;
import java.util.LinkedHashSet;
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
    boolean updateHidden(Long id, Integer hidden);

    /**
     * 根据当前用户获取菜单
     * @param currentUserId 当前用户
     * @return 菜单
     */
    List<MenuEntity> findByUser(Long currentUserId);


    /**
     * 构建菜单树
     * @param menus 原始数据
     * @return /
     */
    List<MenuVo> buildTree(List<MenuEntity> menus);
    /**
     * 构建菜单树
     * @param menus /
     * @return /
     */
    List<MenuVo> buildMenus(List<MenuVo> menus);
}
