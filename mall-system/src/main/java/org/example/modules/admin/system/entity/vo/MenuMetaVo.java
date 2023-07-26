package org.example.modules.admin.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-07-26
 *
 * @author Dou-Fu-10
 * @date 2023-07-26
 * @Description 后台菜单表(Menu)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuMetaVo implements Serializable {
    /**
     * 菜单标题
     */
    private String title;

    /**
     * 前端图标
     */
    private String icon;
}
