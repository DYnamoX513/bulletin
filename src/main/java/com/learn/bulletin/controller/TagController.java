package com.learn.bulletin.controller;

import com.google.common.collect.ImmutableMap;
import com.learn.bulletin.entity.Tag;
import com.learn.bulletin.entity.Wrapper;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import com.learn.bulletin.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(tags = {"标签信息管理"})
@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("获取所有标签信息")
    @GetMapping("/tags")
    public List<Tag> getTags(){
        return tagService.getTags();
    }

    @ApiOperation("查找某条新闻的所有标签内容")
    @GetMapping("/tags/{news_id}")
    @ApiImplicitParam(name = "news_id", value = "新闻ID", paramType = "path", required = true, dataType = "int")
    public List<Tag> getTagsByNews(@PathVariable("news_id") int id) {
        return tagService.getTagsByNews(id);
    }

    @ApiOperation("查找拥有某一标签的所有新闻ID")
    @GetMapping("/tags/{tags}/news_id")
    @ApiImplicitParam(name = "tags", value = "标签名称，多个标签中用下划线分隔(_)", paramType = "path", required = true, dataType = "String")
    public List<Tag> getTagsByTag(@PathVariable("tags") String tags) {
        return tagService.getTagsByNames(Arrays.asList(tags.split("_")));
    }

    @ApiOperation("为某条新闻添加一条标签")
    @PostMapping("/tags")
    public Wrapper<Tag> addNewsTag(@RequestBody Tag tag) {
        if (tagService.addNewTag(tag) == 0) {
            throw new BaseException(ErrorCode.INSERT_FAILED,ImmutableMap.of("news_id",tag.getNews_id(),"tag",tag),null);
        }
        return new Wrapper<>(1000,"添加成功",tag);
    }

//    @PutMapping("/tags")

    @ApiOperation("删除新闻的一条标签")
    @DeleteMapping("/tags")
    public Wrapper<Tag> deleteTag(@RequestBody Tag tag) {
        if (tagService.deleteTag(tag) == 0) {
            throw new BaseException(ErrorCode.DELETE_FAILED,ImmutableMap.of("news_id",tag.getNews_id(),"tag",tag),null);
        }
        return new Wrapper<>(1000,"删除成功",null);
    }
}
