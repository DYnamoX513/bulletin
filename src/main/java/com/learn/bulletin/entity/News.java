package com.learn.bulletin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    private Column column;

    @ApiModelProperty(value = "拥有标签")
    private List<Tag> tags;

    @ApiModelProperty(hidden = true ,example = "0")
    private Date publish_time;

    @ApiModelProperty(hidden = true ,example = "0")
    private Date update_time;

}
