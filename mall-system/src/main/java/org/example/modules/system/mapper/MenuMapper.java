package org.example.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.system.entity.MenuEntity;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:13
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:13
 * @Description 后台菜单表(Menu)表数据库访问层
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {
    LinkedHashSet<MenuEntity> findByRoleIdsAndTypeNot(Set<Long> roleIds, int i);
}

