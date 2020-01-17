package com.learn.bulletin.service;

import com.google.common.collect.ImmutableMap;
import com.learn.bulletin.dao.UserDao;
import com.learn.bulletin.entity.User;
import com.learn.bulletin.exception.BaseException;
import com.learn.bulletin.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDao {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TempTableService tempTableService;

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserByMobile(String mobile) {
        return userDao.getUserByMobile(mobile);
    }

    @Override
    public int insertUser(User user) {
         if (getUserByMobile(user.getMobile()) != null) {
             throw new BaseException(ErrorCode.DUPLICATED_MOBILE,ImmutableMap.of("mobile",user.getMobile()));
         }
         try {
             tempTableService.preventDuplicated("mobile", 11, user.getMobile());
         } catch (RuntimeException ex) {
             throw new BaseException(ErrorCode.DUPLICATED_MOBILE,ImmutableMap.of("mobile",user.getMobile()),ex.getCause());
         }
         return userDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int updateUserPassword(int id, String password) {
        return userDao.updateUserPassword(id,password);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
}
