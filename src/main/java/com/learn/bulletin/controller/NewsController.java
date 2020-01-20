package com.learn.bulletin.controller;

import com.google.common.collect.ImmutableMap;
import com.learn.bulletin.entity.Column;
import com.learn.bulletin.entity.News;
import com.learn.bulletin.entity.Wrapper;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import com.learn.bulletin.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(tags = {"新闻内容管理"})
@RestController
@RequestMapping("/api")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @ApiOperation("获取用户发布的新闻")
    @ApiImplicitParam(name = "news_id", value = "新闻ID", paramType = "path", required = true, dataType = "int")
    @GetMapping("/news/{news_id}")
    public News getNewsById(@PathVariable("news_id") int id){
        return newsService.getNewsById(id);
    }

    @ApiOperation("获取某一栏目的新闻")
    @ApiImplicitParam(name = "column", value = "栏目名称", paramType = "path", required = true, dataType = "String")
    @GetMapping("/news/column/{column}")
    public List<News> getNewsByColumn(@PathVariable("column") String column) {
        return newsService.getNewsByColumn(column);
    }

    @ApiOperation("获取标签对应的新闻")
    @ApiImplicitParam(name = "tags", value = "标签名称，多个标签中用下划线分隔(_)", paramType = "path", required = true, dataType = "String")
    @GetMapping("/news/tags/{tags}")
    public List<News> getNewsByTags(@PathVariable("tag") String tags){
        return newsService.getNewsByTags(Arrays.asList(tags.split("_")));
    }

    @ApiOperation("发布新闻")
    @PostMapping("/news")
    public Wrapper<News> addNews(@RequestBody News news) {
        news.setPublish_time(new Date());
        if(news.getColumn() == null){
            news.setColumn(new Column("default"));
        }
        if (newsService.addNews(news) == 0)
            throw new BaseException(ErrorCode.INSERT_FAILED,null,null);
        return new Wrapper<>(1000,"发布成功",news);
    }

    @ApiOperation("修改新闻信息")
    @PutMapping("/news/{news_id}")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "tag", value = "是否更新标签", paramType = "query", dataType = "boolean",defaultValue = "false"),
                    @ApiImplicitParam(name = "column", value = "是否更新栏目", paramType = "query", dataType = "boolean",defaultValue = "false"),
                    @ApiImplicitParam(name = "news_id", value = "新闻ID", paramType = "path", dataType = "int",required = true)
            }
    )
    public Wrapper<News> modifyNews(@RequestBody News news, @PathVariable("news_id") int id,
                                    @RequestParam("tag") boolean tag, @RequestParam("column") boolean column){
        news.setUpdate_time(new Date());
        if(column && news.getColumn() == null){
            news.setColumn(new Column("default"));
        }
        news.setNews_id(id);
        newsService.modifyNews(news,tag,column);
        return new Wrapper<>(1000,"修改成功",news);
    }

    @ApiOperation("删除新闻")
    @DeleteMapping("/news/{news_id}")
    @ApiImplicitParam(name = "news_id", value = "新闻ID", paramType = "path", dataType = "int",required = true)
    public Wrapper<News> deleteNews(@PathVariable("news_id") int id) {
        if (newsService.deleteNews(id) == 0)
            throw new BaseException(ErrorCode.DELETE_FAILED, ImmutableMap.of("news_id",id),null);
        return new Wrapper<>(1000,"删除成功",null);
    }
}
