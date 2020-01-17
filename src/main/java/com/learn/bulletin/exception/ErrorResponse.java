package com.learn.bulletin.exception;

import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorResponse {
    private int code;
    private int status;
    private String message;
    private String path;
    private Instant timestamp;
    private Throwable cause;
    private HashMap<String,Object> data = new HashMap<String, Object>();

    public ErrorResponse(){
    }

    public ErrorResponse(BaseException ex, String path) {
        this(ex.getError().getCode(), ex.getError().getStatus().value(), ex.getError().getMessage(), path, ex.getData(),ex.getCause());
    }

    public ErrorResponse(int code, int status, String message, String path, Map<String, Object> data, Throwable cause) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.path = path;
        this.cause = cause;
        this.timestamp = Instant.now();
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public String getCause() {
        return cause == null ? "": cause.getMessage();
    }
}
