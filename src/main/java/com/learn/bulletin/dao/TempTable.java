package com.learn.bulletin.dao;

import org.apache.ibatis.annotations.*;
@Mapper
public interface TempTable {

    /**
     * 创建临时表
     */
    @Update("CREATE TEMPORARY TABLE IF NOT EXISTS ${tableName} (id VARCHAR(${length}) NOT NULL, PRIMARY KEY(id))")
    void createTemporaryTable(@Param("tableName") String tableName, @Param("length") int length);

    @Insert("INSERT into ${tableName}(id) VALUES (#{id})")
    void insertTemporaryTable(@Param("tableName") String tableName, @Param("id") String id);

    /**
     * 删除临时表
     */
    @Update({"DROP TEMPORARY TABLE IF EXISTS ${tableName}"})
    void dropTemporaryTable(@Param("tableName") String tableName);
}
