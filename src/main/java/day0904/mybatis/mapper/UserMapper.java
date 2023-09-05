package day0904.mybatis.mapper;


import day0904.mybatis.po.UserLog;
import day0904.mybatis.po.User;
import day0905.IDb1Connect;

import java.util.List;

public interface UserMapper extends IDb1Connect {

    User getUserById(int id);

    User getUserByEmail(String email);

    List<User> getAllUser();

    int getUserCount();

    List<User> getAPageUser(int page);

    void addUser(User user);

    void addUserLog(UserLog userLog);

    void deleteUser(int id);

    void updateUser(int id, String email, String password);

}
