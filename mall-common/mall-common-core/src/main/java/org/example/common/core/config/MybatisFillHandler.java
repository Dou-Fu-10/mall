package org.example.common.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
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
        Date currentTime = new Date();
        if (metaObject.hasSetter("createTime")) {
            Class<?> clazz = metaObject.getSetterType("createTime");
            if (Long.class.getName().equals(clazz.getName())) {
                setFieldValByName("createTime", System.currentTimeMillis(), metaObject);
            } else {
                setFieldValByName("createTime", currentTime, metaObject);
            }
        }
        if (metaObject.hasSetter("createBy")) {
            setFieldValByName("createBy", getUsername(), metaObject);
        }
        if (metaObject.hasSetter("updateTime")) {
            Class<?> clazz = metaObject.getSetterType("updateTime");
            if (Long.class.getName().equals(clazz.getName())) {
                setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
            } else {
                setFieldValByName("updateTime", currentTime, metaObject);
            }
        }
        if (metaObject.hasSetter("updateBy")) {
            setFieldValByName("updateBy", getUsername(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date currentTime = new Date();
        if (metaObject.hasSetter("updateTime")) {
            Class<?> clazz = metaObject.getSetterType("updateTime");
            if (Long.class.getName().equals(clazz.getName())) {
                setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
            } else {
                setFieldValByName("updateTime", currentTime, metaObject);
            }
        }
        if (metaObject.hasSetter("updateBy")) {
            setFieldValByName("updateBy", getUsername(), metaObject);
        }
    }

    private String getUsername() {
        try {
//            return SecurityUtils.getCurrentUsername();
            return "";
        } catch (Exception e) {
            return "";
        }
    }

}
