package org.example.modules.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.admin.system.entity.AdminLoginLogEntity;

/**
 * Created by Dou-Fu-10 2023-07-09 18:34:52
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:34:52
 * @Description 后台用户登录日志表(AdminLoginLog)表数据库访问层
 */
@Mapper
public interface AdminLoginLogMapper extends BaseMapper<AdminLoginLogEntity> {

}

