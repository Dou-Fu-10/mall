package org.example.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.vo.MenuVo;
import org.example.modules.system.mapper.MenuMapper;
import org.example.modules.system.service.MenuService;
import org.example.modules.system.service.RoleService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:13
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:13
 * @Description 后台菜单表(Menu)表服务实现类
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Resource
    private RoleService roleService;

    @Override
    public boolean updateHidden(Long id, Integer hidden) {
        return false;
    }

    @Override
    public List<MenuEntity> findByUser(Long currentUserId) {
        List<RoleEntity> roles = roleService.findByUsersId(currentUserId);
        Set<Long> roleIds = roles.stream().map(RoleEntity::getId).collect(Collectors.toSet());
        LinkedHashSet<MenuEntity> menus = baseMapper.findByRoleIdsAndTypeNot(roleIds, 2);
        return new ArrayList<>(menus);
    }

    @Override
    public List<MenuVo> buildTree(List<MenuEntity> menus) {
        List<MenuVo> menuVoList = BeanCopy.copytList(menus, MenuVo.class);
        List<MenuVo> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuVo menu : menuVoList) {
            if (menu.getParentId() == null) {
                trees.add(menu);
            }
            for (MenuVo it : menuVoList) {
                if (menu.getId().equals(it.getParentId())) {
                    if (menu.getChildren() == null) {
                        menu.setChildren(new ArrayList<>());
                    }
                    menu.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if (trees.size() == 0) {
            trees = menuVoList.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    @Override
    public List<MenuVo> buildMenus(List<MenuVo> menus) {
//        List<MenuVo> list = new LinkedList<>();
//        menus.forEach(menu -> {
//                    if (menu != null) {
//                        List<MenuVo> menuList = menu.getChildren();
//                        MenuVo menuVo = new MenuVo();
//                        menuVo.setName(ObjectUtil.isNotEmpty(menu.getComponentName()) ? menu.getComponentName() : menu.getTitle());
//                        // 一级目录需要加斜杠，不然会报警告
//                        menuVo.setPath(menu.getParentId() == null ? "/" + menu.getPath() : menu.getPath());
//                        menuVo.setHidden(menu.getHidden());
//                        // 如果不是外链
//                        if (!menu.getIFrame()) {
//                            if (menu.getParentId() == null) {
//                                menuVo.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
//                                // 如果不是一级菜单，并且菜单类型为目录，则代表是多级菜单
//                            } else if (menu.getType() == 0) {
//                                menuVo.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "ParentView" : menu.getComponent());
//                            } else if (StringUtils.isNoneBlank(menu.getComponent())) {
//                                menuVo.setComponent(menu.getComponent());
//                            }
//                        }
//                        menuVo.setMeta(new MenuMetaVo(menu.getTitle(), menu.getIcon(), !menu.getCache()));
//                        if (CollectionUtil.isNotEmpty(menuList)) {
//                            menuVo.setAlwaysShow(true);
//                            menuVo.setRedirect("noredirect");
//                            menuVo.setChildren(buildMenus(menuList));
//                            // 处理是一级菜单并且没有子菜单的情况
//                        } else if (menu.getParentId() == null) {
//                            MenuVo menuVo1 = new MenuVo();
//                            menuVo1.setMeta(menuVo.getMeta());
//                            // 非外链
//                            if (!menu.getIFrame()) {
//                                menuVo1.setPath("index");
//                                menuVo1.setName(menuVo.getName());
//                                menuVo1.setComponent(menuVo.getComponent());
//                            } else {
//                                menuVo1.setPath(menu.getPath());
//                            }
//                            menuVo.setName(null);
//                            menuVo.setMeta(null);
//                            menuVo.setComponent("Layout");
//                            List<MenuVo> list1 = new ArrayList<>();
//                            list1.add(menuVo1);
//                            menuVo.setChildren(list1);
//                        }
//                        list.add(menuVo);
//                    }
//                }
//        );
        return null;
    }
}

