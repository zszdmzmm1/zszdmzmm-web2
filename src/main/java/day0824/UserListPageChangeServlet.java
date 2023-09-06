package day0824;


import day0904.mybatis.po.User;
import day0905.IUserDau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/page-change")
public class UserListPageChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserDau connector = (IUserDau) req.getSession().getServletContext().getAttribute("connector");
        int page = Integer.parseInt(req.getParameter("page"));
        String target = req.getParameter("target");
        if(target.equals("Previous")){
            page -= 1;
        }else{
            page += 1;
        }
        List<User> userList = connector.getAPageUser((page - 1) * 10);
        req.setAttribute("userList", userList);
        req.setAttribute("page", page);
        req.getRequestDispatcher("./page/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
