package com.learn.bulletin.dao;

import com.learn.bulletin.entity.News;
import com.learn.bulletin.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsDao {

    /**
     * 获取id对应用户的新闻
     * @param id user_id
     * @return News集合
     */
    @Select("SELECT * FROM news WHERE user_id = #{user_id}")
    @Results(
            {
                    @Result(property = "news_id",column = "news_id"),
                    @Result(property = "column",column = "column_id",
                            one = @One(select = "com.learn.bulletin.dao.ColumnDao.getColumnById")),
                    @Result(property = "tags",column = "news_id",
                            many = @Many(select = "com.learn.bulletin.dao.TagDao.getTagsByNews")),
            }
    )
    List<News> getNewsByUser(@Param("user_id") int id);

    /**
     * 获取news_id对应的新闻
     * @param id news_id
     * @return News
     */
    @Select("SELECT * FROM news WHERE news_id = #{news_id}")
    @Results(
            {
                    @Result(property = "news_id",column = "news_id"),
                    @Result(property = "column",column = "column_id",
                            one = @One(select = "com.learn.bulletin.dao.ColumnDao.getColumnById")),
                    @Result(property = "tags",column = "news_id",
                            many = @Many(select = "com.learn.bulletin.dao.TagDao.getTagsByNews")),
            }
    )
    News getNewsById(@Param("news_id") int id);

    /**
     * 获取属于某一栏目的新闻
     * @param column 栏目
     * @return News集合
     */
    @Select("SELECT * FROM news n LEFT JOIN columns c " +
            "ON n.column_id = c.column_id " +
            "WHERE c.content = #{column}")
    @Results(
            {
                    @Result(property = "news_id",column = "news_id"),
                    @Result(property = "tags",column = "news_id",
                            many = @Many(select = "com.learn.bulletin.dao.TagDao.getTagsByNews")),
            }
    )
    List<News> getNewsByColumn(@Param("column") String column);


    @Select(
            {"<script>",
                    "SELECT * FROM news where news_id in ",
                    "<foreach collection=\"tags\" item=\"tag\" index=\"index\" open=\"(\" separator=\",\" close=\")\">",
                    "#{tag.news_id}",
                    "</foreach>",
                    "</script>"}
    )
    List<News> getMultiNewsByIdFromTags(@Param("tags") List<Tag> tags);

    @Insert("INSERT INTO news(user_id,title,content,column_id)" +
            " VALUES(#{user_id},#{title},#{content},#{column_id})")
    int addNews(News news);

}
