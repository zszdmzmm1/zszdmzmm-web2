package day0908;

import com.alibaba.fastjson.JSONObject;
import day0904.UserDaoMybatisImpl;
import day0904.mybatis.po.User;
import day0905.UserDao;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Random;

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

    @Override
    public MessageDTO adminService(int page) {
        List<User> userList = userDao.getAPageUser((page - 1) * 10);
        int count = userDao.getUserCount();
        return new MessageDTO(String.valueOf(count), userList, 100);
    }

    @Override
    public MessageDTO userSearchService(int page, String email) {
        List<User> userList = userDao.getUsersByFussyEmailSearch(email, (page - 1) * 10);
        int count = userDao.getUserCountsByFussyEmailSearch(email);
        return new MessageDTO(String.valueOf(count), userList, 100);
    }

    @Override
    public MessageDTO getVerifyCode(String email) {
        User user = userDao.getUserByEmail(email);
        MessageDTO messageDTO = new MessageDTO();
        if(user == null){
            StringBuilder sVerifyCode = new StringBuilder();
            Random random = new Random();
            for(int i = 0; i < 4; i ++){
                sVerifyCode.append(random.nextInt(10));
            }
            String verifyCode = sVerifyCode.toString();
            messageDTO.setObject(verifyCode);
            messageDTO.setCode(100);
        }else{
            messageDTO.setCode(999);
        }
        return messageDTO;
    }

    @Override
    public MessageDTO registerVerify(String email) {
        MessageDTO messageDTO = new MessageDTO();
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            messageDTO.setMessage("邮箱已被占用");
        }
        return messageDTO;
    }

    @Override
    public MessageDTO loginProcessingService(String email, String password) {
        User user = userDao.getUserByEmail(email);
        MessageDTO messageDTO = new MessageDTO();
        if(user == null){
            messageDTO.setCode(999);
            messageDTO.setMessage("未找到该用户！");
        }else{
            messageDTO.setObject(user);
            if(password.equals(user.getPassword())){
                if(user.getRole().equals("管理员")){
                    messageDTO.setCode(100);
                    messageDTO.setMessage("管理员");
                }else{
                    messageDTO.setCode(100);
                    messageDTO.setMessage("用户");
                }
            } else {
                messageDTO.setCode(999);
                messageDTO.setMessage("密码错误！");
            }
        }
        return messageDTO;
    }
}
