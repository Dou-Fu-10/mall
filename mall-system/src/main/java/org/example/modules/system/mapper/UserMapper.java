package org.example.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.system.entity.UserEntity;

/**
 * Created by PanShiFu 2023-07-09 18:34:51
 *
 * @author PanShiFu
 * @date 2023-07-09 18:34:51
 * @Description 后台用户表(User)表数据库访问层
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}

