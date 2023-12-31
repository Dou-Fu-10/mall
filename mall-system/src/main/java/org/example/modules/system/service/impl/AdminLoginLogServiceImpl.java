package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.utils.StringUtils;
import org.example.modules.system.entity.AdminLoginLogEntity;
import org.example.modules.system.mapper.AdminLoginLogMapper;
import org.example.modules.system.service.AdminLoginLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-09 17:33:01
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 17:33:01
 * @Description 后台用户登录日志表(AdminLoginLog)表服务实现类
 */
@Service("adminLoginLogService")
public class AdminLoginLogServiceImpl extends ServiceImpl<AdminLoginLogMapper, AdminLoginLogEntity> implements AdminLoginLogService {

    @Override
    public Boolean insertLoginLog(String username, HttpServletRequest request) {
        if (Objects.isNull(username) || Objects.isNull(request)) {
            return false;
        }
        // 获取ip地址
        String ip = StringUtils.getIp(request);
        // 获取Ip对应的地图地址
        String address = StringUtils.getCityInfo(ip);
        String userAgent = StringUtils.getBrowser(request);
        // 插入数据库
        AdminLoginLogEntity adminLoginLogEntity = new AdminLoginLogEntity(username, new Date(), ip, address, userAgent);
        return adminLoginLogEntity.insert();
    }
}

