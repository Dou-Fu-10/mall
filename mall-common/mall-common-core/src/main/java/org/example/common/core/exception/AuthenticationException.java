package org.example.common.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Dou-Fu-10 2023-07-07 09:58:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-07 09:58:02
 * @Description 异常处理
 */
@Getter
public class AuthenticationException extends RuntimeException {

    /**
     * 默认 400
     */
    private Integer status = BAD_REQUEST.value();

    /**
     * @param msg 异常消息
     */
    public AuthenticationException(String msg) {
        super(msg);
    }

    /**
     * @param status 自定义状态码
     * @param msg    异常消息
     */
    public AuthenticationException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.value();
    }
}
