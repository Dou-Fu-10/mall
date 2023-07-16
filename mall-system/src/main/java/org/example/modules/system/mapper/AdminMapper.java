package org.example.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.common.core.entity.AdminEntity;

/**
 * Created by Dou-Fu-10 2023-07-09 18:34:51
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:34:51
 * @Description 后台用户表(Admin)表数据库访问层
 */
@Mapper
public interface AdminMapper extends BaseMapper<AdminEntity> {

}

