package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.mapper.MenuMapper;
import org.example.modules.system.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-09 18:52:13
 *
 * @author PanShiFu
 * @date 2023-07-09 18:52:13
 * @Description 后台菜单表(Menu)表服务实现类
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Override
    public boolean updateHidden(Long id, Integer hidden) {
        return false;
    }
}

