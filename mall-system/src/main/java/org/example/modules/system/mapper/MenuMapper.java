package org.example.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.system.entity.MenuEntity;

/**
 * Created by PanShiFu 2023-07-09 18:52:13
 *
 * @author PanShiFu
 * @date 2023-07-09 18:52:13
 * @Description 后台菜单表(Menu)表数据库访问层
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {

}

