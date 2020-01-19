package com.learn.bulletin.service;

import com.google.common.collect.ImmutableMap;
import com.learn.bulletin.dao.NewsDao;
import com.learn.bulletin.entity.Column;
import com.learn.bulletin.entity.News;
import com.learn.bulletin.entity.Tag;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsService implements NewsDao {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private TagService tagService;

    @Autowired ColumnService columnService;

    @Override
    public List<News> getNewsByUser(int id) {
        return newsDao.getNewsByUser(id);
    }

    @Override
    public News getNewsById(int id) {
        return newsDao.getNewsById(id);
    }

    @Override
    public List<News> getNewsByColumn(String column) {
        return newsDao.getNewsByColumn(column);
    }

    @Override
    public List<News> getMultiNewsByIdFromTags(List<Tag> tags) {
        return newsDao.getMultiNewsByIdFromTags(tags);
    }

    @Transactional
    @Override
    public int addNews(News news) {
        Column c = columnService.getColumnByName(news.getColumn().getContent());
        if(c == null){
            throw new BaseException(ErrorCode.COLUMN_NOT_EXIST, ImmutableMap.of("column",news.getColumn().getContent()),null);
        }
        news.setColumn(c);
        int result = newsDao.addNews(news);
        if (result != 0 && news.getTags()!=null) {
            int id = news.getNews_id();
            for (Tag t:news.getTags()) {
                t.setNews_id(id);
                result &= tagService.addNewTag(t);
            }
        }
        return result;
    }

    @Override
    public int modifyNews(News news) {
        return newsDao.modifyNews(news);
    }

    @Override
    public int modifyNewsContent(News news) {
        return newsDao.modifyNewsContent(news);
    }

    @Transactional
    public void modifyNews(News news, boolean tag,boolean column){
        if (newsDao.getNewsById(news.getNews_id()) == null){
            throw new BaseException(ErrorCode.NEWS_NOT_EXIST,ImmutableMap.of(("news_id"),news.getNews_id()),null);
        }
        if (column) {
            Column c = columnService.getColumnByName(news.getColumn().getContent());
            if(c == null){
                throw new BaseException(ErrorCode.COLUMN_NOT_EXIST, ImmutableMap.of("column",news.getColumn().getContent()),null);
            }
            news.setColumn(c);
            modifyNews(news);
        } else {
            modifyNewsContent(news);
        }
        if (tag){
            int id = news.getNews_id();
            tagService.resetTag(id);
            for (Tag t:news.getTags()) {
                t.setNews_id(id);
                tagService.addNewTag(t);
            }
        }
    }

    public List<News> getNewsByTags(List<String> tags) {
        List<Tag> result = tagService.getTagsByNames(tags);
        if (result.isEmpty())
            return null;
        return getMultiNewsByIdFromTags(result);
    }

}
