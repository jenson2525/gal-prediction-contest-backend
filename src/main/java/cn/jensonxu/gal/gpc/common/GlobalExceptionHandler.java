package cn.jensonxu.gal.gpc.common;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<Void> handleIllegalArgument(IllegalArgumentException e) {
        return CommonResult.error(400, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<Void> handleException(Exception e) {
        return CommonResult.error(500, "服务器内部错误：" + e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<Void> handleDuplicateKey(DuplicateKeyException e) {
        return CommonResult.error(400, "该QQ号已提交过竞猜，不能重复提交");
    }
}