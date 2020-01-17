package com.learn.bulletin.service;

import com.learn.bulletin.dao.TempTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TempTableService implements TempTable {

    @Autowired
    private TempTable tempTable;

    @Override
    public void createTemporaryTable(String tableName, int length) {
        tempTable.createTemporaryTable(tableName,length);
    }

    @Override
    public void insertTemporaryTable(String tableName, String id) {
        tempTable.insertTemporaryTable(tableName,id);
    }

    @Override
    public void dropTemporaryTable(String tableName) {
        tempTable.dropTemporaryTable(tableName);
    }

    public void preventDuplicated(String tableName, int length, String id) throws RuntimeException{
        tempTable.createTemporaryTable(tableName,length);
        tempTable.insertTemporaryTable(tableName,id);
    }
}
