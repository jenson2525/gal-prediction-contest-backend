package cn.jensonxu.gal.gpc.common;

import lombok.Data;

/*
 *  统一响应体封装类
 */
@Data
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> CommonResult<T> success() {
        return success(null);
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}