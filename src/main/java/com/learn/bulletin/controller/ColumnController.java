package com.learn.bulletin.controller;

import com.learn.bulletin.entity.Column;
import com.learn.bulletin.entity.Wrapper;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import com.learn.bulletin.service.ColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"栏目信息管理"})
@RestController
@RequestMapping("/api")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @ApiOperation("查询所有栏目信息")
    @GetMapping("/columns")
    public List<Column> getColumns() {
        return columnService.getColumns();
    }

    @ApiOperation("查询ID对应的栏目信息")
    @GetMapping("/columns/{column_id}")
    @ApiImplicitParam(name = "column_id", value = "栏目ID", paramType = "path", required = true, dataType = "int")
    public Column getColumnById(@PathVariable("column_id") int id){
        return columnService.getColumnById(id);
    }

    @ApiOperation("查询栏目名称对应的ID")
    @GetMapping("/columns/content/{content}")
    @ApiImplicitParam(name = "content", value = "栏目名称", paramType = "path", required = true, dataType = "String")
    public Column getColumnByName(@PathVariable("content") String content){
        return columnService.getColumnByName(content);
    }

    @ApiOperation("新增栏目")
    @PostMapping("/columns")
    public Wrapper<Column> addColumn(@RequestBody Column column) {
        int result = columnService.addColumn(column);
        if (result == 0)
            throw new BaseException(ErrorCode.INSERT_FAILED,null);
        return new Wrapper<>(1000,"新建成功",column);
    }

    @ApiOperation("更新栏目名称")
    @PutMapping("/columns/{column_id}")
    @ApiImplicitParam(name = "column_id", value = "栏目ID", paramType = "path", required = true, dataType = "int")
    public Wrapper<Column> modifyContent(@PathVariable("column_id") int id, @RequestBody Column column) {
        if (id == 1)
            throw new BaseException(ErrorCode.UPDATE_FAILED,null);
        column.setColumn_id(id);
        int result = columnService.updateColumn(column);
        if (result == 0)
            throw new BaseException(ErrorCode.UPDATE_FAILED,null);
        return new Wrapper<>(1000,"修改成功",column);
    }

    @ApiOperation("删除栏目")
    @DeleteMapping("/columns/{column_id}")
    @ApiImplicitParam(name = "column_id", value = "栏目ID", paramType = "path", required = true, dataType = "int")
    public Wrapper<Column> deleteColumn(@PathVariable("column_id") int id) {
        if (columnService.deleteColumn(id) == 0) {
            throw new BaseException(ErrorCode.DELETE_FAILED,null,null);
        }
        return new Wrapper<>(1000,"删除成功",null);
    }

}