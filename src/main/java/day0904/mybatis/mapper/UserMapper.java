package day0904.mybatis.mapper;


import day0904.mybatis.po.UserLog;
import day0904.mybatis.po.User;
import day0905.IUserDau;

import java.util.List;

public interface UserMapper extends IUserDau {

    User getUserById(int id);

    User getUserByEmail(String email);

    List<User> getAllUser();

    int getUserCount();

    List<User> getAPageUser(int page);

    void addUser(User user);

    void addUserLog(UserLog userLog);

    void deleteUser(int id);

    void updateUser(int id, String email, String password);

    List<User> getUsersByFussyEmailSearch(String email, int page);


    int getUserCountsByFussyEmailSearch(String email);

}
