package org.example.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.example.security.utils.SecurityUtils;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author Dou-Fu-10
 * @date 2023-07-07
 * @Description MybatisPlus 自动更新处理器
 */
@Configuration
public class MybatisFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        // 获取时间
        Date currentTime = new Date();
        // 判断是否拥有 createTime
        if (metaObject.hasSetter("createTime")) {
            Class<?> clazz = metaObject.getSetterType("createTime");
            if (Long.class.getName().equals(clazz.getName())) {
                setFieldValByName("createTime", System.currentTimeMillis(), metaObject);
            } else {
                setFieldValByName("createTime", currentTime, metaObject);
            }
        }
        // 判断是否拥有 createBy
        if (metaObject.hasSetter("createBy")) {
            setFieldValByName("createBy", getUsername(), metaObject);
        }
        // 判断是否拥有 updateTime
        if (metaObject.hasSetter("updateTime")) {
            Class<?> clazz = metaObject.getSetterType("updateTime");
            if (Long.class.getName().equals(clazz.getName())) {
                setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
            } else {
                setFieldValByName("updateTime", currentTime, metaObject);
            }
        }
        // 判断是否拥有 updateTime
        if (metaObject.hasSetter("updateBy")) {
            setFieldValByName("updateBy", getUsername(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date currentTime = new Date();
        // 判断是否拥有 updateTime
        if (metaObject.hasSetter("updateTime")) {
            Class<?> clazz = metaObject.getSetterType("updateTime");
            if (Long.class.getName().equals(clazz.getName())) {
                setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
            } else {
                setFieldValByName("updateTime", currentTime, metaObject);
            }
        }
        // 判断是否拥有 updateBy
        if (metaObject.hasSetter("updateBy")) {
            setFieldValByName("updateBy", getUsername(), metaObject);
        }
    }

    // 获取登录名字
    private String getUsername() {
        try {
            return SecurityUtils.getCurrentUsername();
        } catch (Exception e) {
            return "";
        }
    }

}
