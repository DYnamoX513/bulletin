package com.learn.bulletin.dao;

import com.learn.bulletin.entity.Column;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ColumnDao {

    @Select(("SELECT * FROM columns"))
    List<Column> getColumns();

    @Select("SELECT * FROM columns WHERE column_id = #{column_id}")
    Column getColumnById(@Param("column_id") int id);

    @Select("SELECT * FROM columns WHERE cotent = #{content}")
    Column getColumnByName(@Param("content") String content);

    @Options(useGeneratedKeys = true, keyProperty = "column_id", keyColumn = "column_id")
    @Insert({"INSERT INTO columns(content) VALUES(#{content}"})
    int addColumn(Column column);

    @Update("UPDATE columns SET content = #{content} WHERE column_id = #{column_id}")
    int updateColumn(Column column);

    @Delete("DELETE from columns WHERE column_id = #{column_id}")
    int deleteColumn(@Param("column_id") int id);

    @Update("UPDATE news SET column_id = 1 WHERE column_id = #{column_id)")
    int setColumnDefault(@Param("column_id") int id);
}
