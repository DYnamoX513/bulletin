package com.learn.bulletin.exception;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class BaseException extends RuntimeException{
    private final ErrorCode error;
    private final HashMap<String, Object> data = new HashMap<>();

    public BaseException(ErrorCode error, Map<String, Object> data) {
        super(error.getMessage());
        this.error = error;
        if(!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public BaseException (ErrorCode error, Map<String, Object> data , Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public ErrorCode getError(){
        return error;
    }

    public Map<String, Object> getData(){
        return data;
    }
}
