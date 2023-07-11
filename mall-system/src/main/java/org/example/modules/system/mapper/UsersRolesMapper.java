package org.example.modules.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.system.entity.UsersRolesEntity;

/**
 * Created by PanShiFu 2023-07-09 19:57:25
 *
 * @author PanShiFu
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(UserRole)表数据库访问层
 */
@Mapper
public interface UsersRolesMapper extends BaseMapper<UsersRolesEntity> {

}

