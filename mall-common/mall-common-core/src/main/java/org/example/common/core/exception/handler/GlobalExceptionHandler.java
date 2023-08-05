package org.example.common.core.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;


/**
 * Created by Dou-Fu-10 2023-07-07 09:58:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-07 09:58:02
 * @Description 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleException(Throwable e) {
        // 打印堆栈信息
        log.error(getStackTrace(e));
        return buildResponseEntity(BaseError.error(e.getMessage()));
    }

    /**
     * 参数不匹配异常
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(@NotNull MethodArgumentNotValidException e) {
        log.error("------->MethodArgumentNotValidException参数异常-------- " + Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return buildResponseEntity(BaseError.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }


    /**
     * 统一异常返回
     */
    @NotNull
    @Contract("_ -> new")
    private ResponseEntity<BaseError> buildResponseEntity(BaseError baseError) {
        return new ResponseEntity<>(baseError, HttpStatus.valueOf(baseError.getStatus()));
    }
}
