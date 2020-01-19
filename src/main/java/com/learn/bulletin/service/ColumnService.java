package com.learn.bulletin.service;

import com.google.common.collect.ImmutableMap;
import com.learn.bulletin.dao.ColumnDao;
import com.learn.bulletin.entity.Column;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColumnService implements ColumnDao{

    @Autowired
    private ColumnDao columnDao;

    @Autowired
    private TempTableService tempTableService;

    @Override
    public List<Column> getColumns() {
        return columnDao.getColumns();
    }

    @Override
    public Column getColumnById(int id) {
        return columnDao.getColumnById(id);
    }

    @Override
    public Column getColumnByName(String content) {
        return columnDao.getColumnByName(content);
    }

    @Override
    public int addColumn(Column column) {
        ensureUniqueColumn(column);
        return columnDao.addColumn(column);
    }

    @Override
    public int updateColumn(Column column) {
        ensureUniqueColumn(column);
        return columnDao.updateColumn(column);
    }

    //Fixme: 丢失更新？
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public int deleteColumn(int id) {
        setColumnDefault(id);
        return columnDao.deleteColumn(id);
    }

    @Transactional
    @Override
    public int setColumnDefault(int id) {
        return columnDao.setColumnDefault(id);
    }

    public void ensureUniqueColumn(@org.jetbrains.annotations.NotNull Column column){
        if (columnDao.getColumnByName(column.getContent()) != null) {
            throw new BaseException(ErrorCode.DUPLICATED_COLUMN, ImmutableMap.of("column",column.getContent()),null);
        }
        try{
            tempTableService.preventDuplicated("temp_column",20,column.getContent());
        }catch (RuntimeException ex) {
            throw new BaseException(ErrorCode.DUPLICATED_COLUMN,ImmutableMap.of("column",column.getContent()),ex.getCause());
        }
    }
}
