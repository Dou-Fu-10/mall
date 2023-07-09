package org.example.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.system.entity.RoleEntity;

/**
 * Created by PanShiFu 2023-07-09 18:34:52
 *
 * @author PanShiFu
 * @date 2023-07-09 18:34:52
 * @Description 后台用户角色表(Role)表数据库访问层
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

}

