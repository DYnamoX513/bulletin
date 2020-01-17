package com.learn.bulletin.service;

import com.google.common.collect.ImmutableMap;
import com.learn.bulletin.dao.TagDao;
import com.learn.bulletin.entity.Tag;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements TagDao {

    @Autowired
    private TagDao tagDao;

    @Override
    public List<Tag> getTags() {
        return tagDao.getTags();
    }

    @Override
    public List<Tag> getTagsByNews(int id) {
        return tagDao.getTagsByNews(id);
    }

    @Override
    public List<Tag> getTagsByNames(List<String> tags) {
        return tagDao.getTagsByNames(tags);
    }

    @Override
    public int addNewTag(Tag tag) {
        try {
            return tagDao.addNewTag(tag);
        } catch (RuntimeException ex) {
            throw new BaseException(ErrorCode.DUPLICATED_TAG, ImmutableMap.of("news_id",tag.getNews_id(),"tag",tag),ex.getCause());
        }
    }

    @Override
    public int updateTag(int id, String oldTag, String newTag) {
        try {
            return tagDao.updateTag(id, oldTag, newTag);
        } catch (RuntimeException ex) {
            throw new BaseException(ErrorCode.DUPLICATED_TAG, ImmutableMap.of("news_id",id,"tag",newTag),ex.getCause());
        }
    }

    @Override
    public int deleteTag(Tag tag) {
        return tagDao.deleteTag(tag);
    }
}
