package com.learn.bulletin.controller;

import com.learn.bulletin.entity.News;
import com.learn.bulletin.entity.Wrapper;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import com.learn.bulletin.service.ColumnService;
import com.learn.bulletin.service.NewsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(tags = {"新闻内容管理"})
@RestController
@RequestMapping("/api")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private ColumnService columnService;

    @GetMapping("/news/{news_id}")
    public News getNewsById(@PathVariable("news_id") int id){
        return newsService.getNewsById(id);
    }

    @GetMapping("/news/column/{column_id}")
    public List<News> getNewsByColumn(@PathVariable("column_id") int id) {
        return newsService.getNewsByColumn(id);
    }

    @GetMapping("/news/tags/{tag}")
    public List<News> getNewsByTags(@PathVariable("tag") String tag){
        return newsService.getNewsByTags(Arrays.asList(tag.split("_")));
    }

    @PostMapping("/news")
    public Wrapper<News> addNews(@RequestBody News news) {
        if (newsService.addNews(news) == 0)
            throw new BaseException(ErrorCode.INSERT_FAILED,null,null);
        return new Wrapper<>(1000,"发布成功",news);
    }
}
