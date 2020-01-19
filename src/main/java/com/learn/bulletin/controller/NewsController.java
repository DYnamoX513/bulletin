package com.learn.bulletin.controller;

import com.learn.bulletin.entity.Column;
import com.learn.bulletin.entity.News;
import com.learn.bulletin.entity.Wrapper;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import com.learn.bulletin.service.ColumnService;
import com.learn.bulletin.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

    @GetMapping("/news/column/{column}")
    public List<News> getNewsByColumn(@PathVariable("column") String column) {
        return newsService.getNewsByColumn(column);
    }

    @GetMapping("/news/tags/{tag}")
    public List<News> getNewsByTags(@PathVariable("tag") String tag){
        return newsService.getNewsByTags(Arrays.asList(tag.split("_")));
    }

    @PostMapping("/news")
    public Wrapper<News> addNews(@RequestBody News news) {
        if(news.getColumn() == null){
            news.setColumn(new Column("default"));
        }
        if (newsService.addNews(news) == 0)
            throw new BaseException(ErrorCode.INSERT_FAILED,null,null);
        return new Wrapper<>(1000,"发布成功",news);
    }

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
        if(column && news.getColumn() == null){
            news.setColumn(new Column("default"));
        }
        news.setNews_id(id);
        newsService.modifyNews(news,tag,column);
        return new Wrapper<>(1000,"修改成功",news);
    }
}
