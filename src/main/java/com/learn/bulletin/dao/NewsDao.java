package com.learn.bulletin.dao;

import com.learn.bulletin.entity.News;
import com.learn.bulletin.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface NewsDao {

    /**
     * 获取id对应用户的新闻
     * @param id user_id
     * @return News集合
     */
    @Select("SELECT * FROM news WHERE user_id = #{user_id}")
    List<News> getNewsByUser(@Param("user_id") int id);

    /**
     * 获取news_id对应的新闻
     * @param id news_id
     * @return News
     */
    @Select("SELECT * FROM news WHERE news_id = #{news_id}")
    News getNewsById(@Param("news_id") int id);

    /**
     * 获取属于column_id栏目的新闻
     * @param id column_id
     * @return News集合
     */
    @Select("SELECT * FROM news WHERE column_id = #{column_id}")
    List<News> getNewsByColumn(@Param("column_id") int id);


    /**
     * 获取多个id对应的新闻
     * @param id_list 多个news_id
     * @return  News集合
     */

    @Select(
            {"<script>",
                    "SELECT * FROM news where news_id in ",
                    "<foreach collection=\"id_list\" item=\"id\" index=\"index\" open=\"(\" separator=\",\" close=\")\">",
                    "#{id}",
                    "</foreach>",
                    "</script>"}
    )
    List<News> getMultiNewsById(@Param("id_list") List<Integer> id_list);

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
