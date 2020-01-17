package com.learn.bulletin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("栏目信息")
@Data
public class Column {
    @ApiModelProperty(value = "栏目ID",example = "0",hidden = true)
    private Integer column_id;

    @ApiModelProperty(value = "栏目名称",example = "international")
    private String content;
}
