package com.learn.bulletin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("新闻信息")
@Data
public class News {

    @ApiModelProperty(value = "所属用户id",example = "0")
    private Integer user_id;

    @ApiModelProperty(hidden = true ,example = "0")
    private Integer news_id;

    @ApiModelProperty(value = "新闻标题", example = "Title")
    private String title;

    @ApiModelProperty(value = "新闻内容", example = "Breaking news")
    private String content;

    @ApiModelProperty(value = "所属栏目" , example = "Financial")
    private Integer column_id;
}
