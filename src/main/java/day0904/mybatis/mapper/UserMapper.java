package day0904.mybatis.mapper;


import day0818.UserLog;
import day0904.mybatis.po.User;

import java.util.List;

public interface UserMapper {

    User getUserById(int id);

    User getUserByEmail(String email);

    List<User> getAllUser();

    int getUserCount();

    List<User> getAPageUser(int page);

    void addUser(User user);

    void addUserLog(UserLog userLog);

    void deleteUser(int id);

    void update(int id, String email, String password);
}
