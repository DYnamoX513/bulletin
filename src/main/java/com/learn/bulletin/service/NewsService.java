package com.learn.bulletin.service;

import com.learn.bulletin.dao.NewsDao;
import com.learn.bulletin.entity.News;
import com.learn.bulletin.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements NewsDao {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private TagService tagService;

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

    @Override
    public int addNews(News news) {
        return newsDao.addNews(news);
    }

    public List<News> getNewsByTags(List<String> tags) {
        List<Tag> result = tagService.getTagsByNames(tags);
        if (result.isEmpty())
            return null;
        return getMultiNewsByIdFromTags(result);
    }
}
