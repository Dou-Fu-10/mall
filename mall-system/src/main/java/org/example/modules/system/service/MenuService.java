package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.system.entity.MenuEntity;


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
}
