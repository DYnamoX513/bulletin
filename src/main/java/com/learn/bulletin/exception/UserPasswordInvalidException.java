package com.learn.bulletin.exception;

import java.util.Map;

public class UserPasswordInvalidException extends BaseException{
    public UserPasswordInvalidException(Map<String,Object> data){
        super(ErrorCode.USER_PASSWORD_INVALID,data);
    }
}
