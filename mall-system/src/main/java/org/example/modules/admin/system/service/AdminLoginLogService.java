package org.example.modules.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.example.modules.admin.system.entity.AdminLoginLogEntity;


/**
 * Created by Dou-Fu-10 2023-07-09 17:33:01
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 17:33:01
 * @Description 后台用户登录日志表(AdminLoginLog)表服务接口
 */
public interface AdminLoginLogService extends IService<AdminLoginLogEntity> {
    /**
     * 记录登陆者信息
     *
     * @param username 登录用户名
     * @param request  Http Servlet请求
     * @return 是否成功
     */
    Boolean insertLoginLog(String username, HttpServletRequest request);
}
