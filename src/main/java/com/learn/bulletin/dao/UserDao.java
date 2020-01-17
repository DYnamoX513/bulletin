package com.learn.bulletin.dao;

import com.learn.bulletin.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 查询所有用户信息
     * @return List
     */
    @Select("SELECT * FROM users")
    List<User> getUsers();

    /**
     * 查询用户ID对应的信息
     * @param id 用户ID
     * @return User
     */
    @Select("SELECT * FROM users WHERE user_id = #{id}")
    User getUserById(@Param("id") int id);

    /**
     * 查询用户名对应的信息
     * @param name 用户名
     * @return User集合
     */

    @Select("SELECT * FROM users WHERE user_name = #{name}")
    List<User> getUserByName(@Param("name") String name);

    @Select("SELECT * FROM users WHERE mobile = #{mobile}")
    User getUserByMobile(@Param("mobile") String mobile);

    /**
     * 新建用户
     * @param user 用户User
     * @return 增加结果
     */
    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    @Insert("INSERT INTO users(user_name, password, email, mobile, gender, age) " +
            "VALUES( #{user_name}, #{password}, #{email}, #{mobile}, #{gender}, #{age})")
    int insertUser(User user);


    /**
     * 更新用户资料
     * @param user 用户
     * @return 更新结果
     */
    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    @Update("UPDATE users SET user_name = #{user_name}, email = #{email}, mobile = #{mobile}, gender = #{gender}, age = #{age} " +
            "WHERE user_id = #{user_id}")
    int updateUser(User user);


    /**
     * 更新用户密码
     * @param id 用户名
     * @param password 密码
     * @return 更新结果
     */
    @Update("UPDATE users SET password = #{password} WHERE user_id = #{user_id}")
    int updateUserPassword(@Param("user_id") int id, @Param("password")String password);

    /**
     * 删除ID对应的用户
     * @param id user_id
     * @return 删除结果
     */
    @Delete("DELETE from users WHERE user_id = #{id}")
    int deleteUser(@Param("id") int id);

    @Select("SELECT * FROM users WHERE user_id = #{id}")
    @Results(
            {
                    @Result(property = "user_id",column = "user_id"),
                    @Result(property = "news",column = "user_id",
                    many = @Many(select = "com.learn.bulletin.dao.NewsDao.getNewsByUser"))
            }
    )
    User getUserNews(@Param("id") int id);

}
