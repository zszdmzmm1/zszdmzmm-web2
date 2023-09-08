package day0908;

import day0904.UserDaoMybatisImpl;
import day0904.mybatis.po.User;
import day0905.UserDao;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final UserDao userDao;

    private UserServiceImpl() {
    }

    private static final class UserServiceImplHolder {
        private static final UserServiceImpl USER_SERVICE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.USER_SERVICE;
    }

    static {
        userDao = UserDaoMybatisImpl.mapper;
    }

    @Override
    public MessageDTO registerProcessingService(User inputUser) {
        User dbUser = userDao.getUserByEmail(inputUser.getEmail());
        String message;
        int code;
        if (dbUser != null) {
            message = "该用户已存在";
            code = 999;
        } else {
            userDao.addUser(inputUser);
            dbUser = userDao.getUserByEmail(inputUser.getEmail());
            message = "success";
            code = 100;
        }
        return new MessageDTO(message, dbUser, code);
    }

    @Override
    public MessageDTO addUserService(User inputUser) {
        MessageDTO messageDTO = new MessageDTO();
        String email = inputUser.getEmail();
        String password = inputUser.getPassword();
        if ("".equals(email) || "".equals(password)) {
            messageDTO.setCode(999);
        } else {
            messageDTO.setCode(100);
            userDao.addUser(inputUser);
        }
        return messageDTO;
    }

    @Override
    public void deleteUserService(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void deleteUsersService(List<String> idList) {
        for (String id : idList) {
            userDao.deleteUser(Integer.parseInt(id.substring(1)));
        }
    }

    @Override
    public void updateService(int id, User updateUser) {
        User dbUser = userDao.getUserById(id);
        String email = updateUser.getEmail();
        String password = updateUser.getPassword();
        if("".equals(email)){
            email = dbUser.getEmail();
        }
        if("".equals(password)){
            password = dbUser.getPassword();
        }
        userDao.updateUser(id, email, password);
    }

    @Override
    public MessageDTO getAPageUserService(int page) {
        MessageDTO messageDTO = new MessageDTO();
        List<User> userList = userDao.getAPageUser((page - 1) * 10);
        messageDTO.setObject(userList);
        return messageDTO;
    }
}
