package com.learn.bulletin.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    SUCCESS(1000,HttpStatus.OK,"成功"),

    DUPLICATED_MOBILE(1001,"手机号已使用"),
    USER_PASSWORD_INVALID(1002,"密码不符合要求"),
    REGISTER_FAILED(1003,"注册失败"),
    UPDATE_FAILED(1004,"更新失败"),
    PASSWORD_INCORRECT(1005,"密码错误"),
    DELETE_FAILED(1006, "删除失败"),
    INSERT_FAILED(1007,"添加失败"),

    DUPLICATED_COLUMN(2001,"栏目名称重复"),
    DUPLICATED_TAG(2002,"标签重复");

    private final int code;

    private final HttpStatus status;

    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
