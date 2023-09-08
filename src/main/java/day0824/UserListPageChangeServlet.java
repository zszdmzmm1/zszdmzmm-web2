package day0824;


import day0908.MessageDTO;
import day0908.UserService;
import day0908.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/page-change")
public class UserListPageChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        String target = req.getParameter("target");
        if(target.equals("Previous")){
            page -= 1;
        }else{
            page += 1;
        }
        UserService userService = UserServiceImpl.getInstance();
        MessageDTO messageDTO  = userService.getAPageUserService(page);
        req.setAttribute("userList", messageDTO.getObject());
        req.setAttribute("page", page);
        req.getRequestDispatcher("./page/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
