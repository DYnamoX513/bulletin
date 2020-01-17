package com.learn.bulletin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("标签信息")
@Data
public class Tag {
    @ApiModelProperty(example = "0",hidden = true)
    private Integer news_id;

    @ApiModelProperty(value = "标签名称", example = "Global warming")
    private String tag;
}
