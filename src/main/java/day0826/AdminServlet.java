package day0826;

import day0904.mybatis.po.User;
import day0905.UserDao;
import day0908.MessageDTO;
import day0908.UserService;
import day0908.UserServiceImpl;
import jakarta.mail.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/admin/user")
public class AdminServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sPage = req.getParameter("page");
        String email = req.getParameter("sEmail");
        String mode = (String) req.getSession().getAttribute("mode");
        int count;
        MessageDTO messageDTO;
        int pageCount;
        UserService userService = UserServiceImpl.getInstance();
        int page = 1;
        if (email == null) {
            email = "";
        }
        if (sPage != null) {
            page = Integer.parseInt(sPage);
        }
        if ("search".equals(mode)) {
            messageDTO = userService.userSearchService(page, email);
        } else {
            messageDTO = userService.adminService(page);
        }
        count = Integer.parseInt(messageDTO.getMessage());
        if (count % 10 == 0) {
            pageCount = count / 10;
        } else {
            pageCount = count / 10 + 1;
        }
        req.setAttribute("sEmail", email);
        req.setAttribute("count", count);
        req.setAttribute("page", page);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("userList", messageDTO.getObject());
        req.getRequestDispatcher("../page/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
