package org.example.common.core.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-07 09:58:02
 * @Description 放回异常格式
 */
@Data
class BaseError {
    /**
     * 默认错误放回
     */
    private Integer status = 400;
    /**
     * 转换成可视时间
     * 当前时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    /**
     * 异常消息
     */
    private String message;

    private BaseError() {
        // 异常时间
        timestamp = LocalDateTime.now();
    }

    /**
     * 使用默认的 400 status
     *
     * @param message 异常消息
     * @return 异常消息
     */
    public static BaseError error(String message) {
        BaseError baseError = new BaseError();
        baseError.setMessage(message);
        return baseError;
    }

    /**
     * 使用自定义 status
     *
     * @param status  状态码
     * @param message 异常消息
     * @return 异常消息
     */
    public static BaseError error(Integer status, String message) {
        BaseError baseError = new BaseError();
        baseError.setStatus(status);
        baseError.setMessage(message);
        return baseError;
    }
}

