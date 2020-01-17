package com.learn.bulletin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Api返回")
@Data
public class Wrapper<T> {

    @ApiModelProperty(value = "返回码", example = "1001")
    private Integer code ;

    @ApiModelProperty(value = "返回信息", example = "成功")
    private String message;
    @ApiModelProperty(value = "数据信息")
    private T data;

    public Wrapper(){}

    public Wrapper(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
