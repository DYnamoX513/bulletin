package com.learn.bulletin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("用户信息")
@Data
public class User {
    @ApiModelProperty(hidden = true,example = "0")
    private Integer user_id;

    @ApiModelProperty(value = "用户名", example = "jack")
    private String user_name;

    @ApiModelProperty(value = "密码", example = "abc12345")
    private String password;

    @ApiModelProperty(value = "电子邮件", example = "example@xxx.com")
    private String email;

    @ApiModelProperty(value = "手机号", example = "13700000000")
    private String mobile;

    @ApiModelProperty(value = "性别\n m（男）/f（女）", example = "m")
    private char gender;

    @ApiModelProperty(value = "年龄", example = "20")
    private short age;

    @ApiModelProperty(value = "新闻发布")
    private List<News> news;
}