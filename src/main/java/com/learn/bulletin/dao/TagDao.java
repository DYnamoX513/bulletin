package com.learn.bulletin.dao;

import com.learn.bulletin.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagDao {
    @Select("SELECT * FROM tags")
    List<Tag> getTags();

    @Select("SELECT * FROM tags WHERE news_id = #{news_id}")
    List<Tag> getTagsByNews(@Param("news_id") int id);

    @Select(
            {"<script>",
                    "SELECT * FROM tags where tag in ",
                    "<foreach collection=\"tags\" item=\"tag\" index=\"index\" open=\"(\" separator=\",\" close=\")\">",
                    "#{tag}",
                    "</foreach>",
                    "</script>"}
    )
    List<Tag> getTagsByNames(@Param("tags") List<String> tags);

    @Insert("INSERT INTO tags(news_id,tag) VALUES(#{news_id},#{tag})")
    int addNewTag(Tag tag);

    @Update("UPDATE tags SET tag = #{newTag} WHERE news_id = #{news_id} AND tag = #{oldTag}")
    int updateTag(@Param("news_id") int id, @Param("oldTag") String oldTag, @Param("newTag") String newTag);

    @Delete("Delete FROM tags WHERE news_id = #{news_id} AND tag = #{tag}")
    int deleteTag(Tag tag);

    @Delete("DELETE FROM tags WHERE news_id = #{news_id}")
    int resetTag(@Param("news_id") int id);
}
