package com.iris.redis.Result;

import com.google.common.base.Strings;
import static com.google.common.base.Preconditions.checkArgument;
import static com.iris.redis.Result.ResultCode.SUCCESS;

public class Result<T> {
    private Boolean success;
    private int code = SUCCESS;
    private String message;
    private T data;

    public Result() {

    }

    private Result(Boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result();
    }

    public static <O> Result<O> successMsg(String message) {
        return new Result<>(true, SUCCESS, message, null);
    }

    public static <O> Result<O> success(O payload) {
        checkArgument(payload != null, "payload should be not null");
        return new Result<>(true, SUCCESS, null, payload);
    }
    
    public static <O> Result<O> success(O data, String message) {
        checkArgument(data != null, "data should not be null");
        return new Result<>(true, SUCCESS, message, data);
    }

    public static <O> Result<O> fail(int code, String message) {
        checkArgument(!Strings.isNullOrEmpty(message), "message should be not empty");
        checkArgument(code > 0, "code should be greater than 0");
        return new Result<>(false, code, message, null);
    }

    public static <O> Result<O> fail(int code, String message, O payload) {
        checkArgument(payload != null, "payload should be not null");
        checkArgument(!Strings.isNullOrEmpty(message), "message should be not empty");
        checkArgument(code > 0, "code should be greater than 0");
        return new Result<>(false, code, message, payload);
    }

    public Boolean getSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
