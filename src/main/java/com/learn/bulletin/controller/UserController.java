package com.learn.bulletin.controller;

import com.learn.bulletin.entity.User;
import com.learn.bulletin.entity.Wrapper;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import com.learn.bulletin.service.UserService;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"用户信息管理"})
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询所有用户信息", authorizations = {
            @Authorization(value = "OAuth2",scopes = {
                    @AuthorizationScope(scope = "read",description = "1234")
            })
    })
    @ApiImplicitParam
    @GetMapping("/users")
    public List<User> getInfo() {
        return userService.getUsers();
    }

    @ApiOperation(value = "查询用户名对应用户的信息" , authorizations = {
            @Authorization(value = "OAuth2",scopes = {
              @AuthorizationScope(scope = "read",description = "1234")
            })
    })
    @ApiImplicitParam(name = "user_name", value = "用户名", paramType = "path", required = true, dataType = "String")
    @GetMapping("/users/name/{user_name}")
    public List<User> getUserIdByName(@PathVariable("user_name") String name){
        return userService.getUserByName(name);
    }

    @ApiOperation("查询ID对应用户的信息")
    @ApiImplicitParam(name = "user_id", value = "用户ID", paramType = "path", required = true, dataType = "int")
    @GetMapping("/users/{user_id}")
    public User getUserNameById(@PathVariable("user_id") Integer id) {
        return userService.getUserById(id);
    }

    @ApiOperation("注册用户")
    @PostMapping("/users")
    public Wrapper<User> register(@RequestBody User user){
        if (user.getPassword().length() < 8 || user.getPassword().length() > 20)
            throw new BaseException(ErrorCode.USER_PASSWORD_INVALID,null);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        int result = userService.insertUser(user);
        if (result == 0)
            throw new BaseException(ErrorCode.REGISTER_FAILED,null);
        return new Wrapper<>(1000,"注册成功",user);
    }

    //TODO: 部分信息唯一性
    @ApiOperation(("修改用户信息(密码无效)"))
    @ApiImplicitParam(name = "user_id", value = "用户id",paramType = "path", required = true,dataType = "int")
    @PutMapping("/users/{user_id}/name")
    public Wrapper<User> modifyInfo(@PathVariable("user_id") Integer id, @RequestBody User user) {
        user.setUser_id(id);
        int result = userService.updateUser(user);
        if (result == 0)
            throw new BaseException(ErrorCode.UPDATE_FAILED,null,null);
        return new Wrapper<>(1000,"修改成功",user);
    }

    @ApiOperation(("修改密码"))
    @ApiImplicitParam(name = "user_id", value = "用户id",paramType = "path", required = true,dataType = "int")
    @PutMapping("/users/{user_id}/password")
    public Wrapper<UserPasswordInfo> modifyPassword(@PathVariable("user_id") int id ,@RequestBody UserPasswordInfo userPasswordInfo) {
        User user = userService.getUserById(id);
        if (userPasswordInfo.getNewPassword().length() < 8 || userPasswordInfo.getNewPassword().length() > 20)
            throw new BaseException(ErrorCode.USER_PASSWORD_INVALID,null);
        userPasswordInfo.md5Convert();
        if (user == null || !user.getPassword().equals(userPasswordInfo.getOldPassword())){
            throw new BaseException(ErrorCode.PASSWORD_INCORRECT,null,null);
        }
        int result = userService.updateUserPassword(id,userPasswordInfo.getNewPassword());
        if (result == 0) {
            throw new BaseException(ErrorCode.UPDATE_FAILED,null,null);
        }
        userPasswordInfo.setUser_id(id);
        return new Wrapper<>(1000,"修改成功",userPasswordInfo);
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "user_id" ,value = "用户ID", paramType = "path", required = true, dataType = "int")
    @DeleteMapping("/users/{user_id}")
    public Wrapper<User> deleteUser(@PathVariable("user_id") Integer id) {
        if (userService.deleteUser(id) == 0) {
            throw new BaseException(ErrorCode.DELETE_FAILED,null,null);
        }
        return new Wrapper<>(1000,"删除成功",null);
    }

    @ApiOperation("获取用户发布的新闻")
    @ApiImplicitParam(name = "user_id" ,value = "用户ID", paramType = "path", required = true, dataType = "int")
    @GetMapping("/user/{user_id}/news")
    public User getUserNews(@PathVariable("user_id") Integer id) {
        return userService.getUserNews(id);
    }

    //TODO: 用户登录及授权
//    @ApiOperation("用户登录")
//    @ApiImplicitParam(name = "user", value = "用户ID，用户名，密码", paramType = "body",  dataType = "User")
//    @PostMapping("/login")
//    public UserBasicInfo login(@RequestBody User user){
//        User result = userService.getUserById(user.getUser_id());
//        if (result == null)
//            throw new UserNotFoundException(new UserBasicInfo(user).generateMap());
//        if (result.getPassword().equals(user.getPassword()) && result.getUser_name().equals(user.getUser_name()))
//            return new UserBasicInfo(user);
//        throw new UserPasswordWrongException(user.generateMap());
//    }
}

@ApiModel("用户密码信息")
@Data
class UserPasswordInfo{
    @ApiModelProperty(hidden = true, example = "123")
    private int user_id;

    @ApiModelProperty(value = "旧密码", example = "old")
    private String oldPassword;
    @ApiModelProperty(value = "新密码", example = "new")
    private String newPassword;

    public void md5Convert(){
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        newPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
    }
}



