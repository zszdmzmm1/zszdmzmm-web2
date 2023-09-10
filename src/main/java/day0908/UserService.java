package day0908;

import day0904.mybatis.po.User;

import java.util.List;

public interface UserService {
    MessageDTO registerProcessingService(User inputUser);

    MessageDTO addUserService(User inputUser);

    void deleteUserService(int id);

    void deleteUsersService(List<String> idList);

    void updateService(int id, User updateUser);

    MessageDTO getAPageUserService(int page);

    MessageDTO adminService(int page);

    MessageDTO userSearchService(int page, String email);

    MessageDTO getVerifyCode(String email);

    MessageDTO registerVerify(String email);

    MessageDTO loginProcessingService(String email, String password);

}
