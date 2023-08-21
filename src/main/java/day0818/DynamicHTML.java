package day0818;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/welcome-page")
public class DynamicHTML extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object o = req.getAttribute("user");
        if(o instanceof String message){
            writeUserInfoHTML(resp, message);
        }else if(o instanceof User user){
            writeUserInfoHTML(resp, user);
        }else{
            List<User> userList = (List<User>) req.getAttribute("user");
            for (User user : userList){
                writeUserInfoHTML(resp, user);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    public void writeUserInfoHTML(HttpServletResponse resp, User user) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>");
        writer.write("<html lang='en'>");
        writer.write("<head>");
        writer.write("<meta charset='UTF-8'>");
        writer.write("<title>" + user.getEmail()  + "</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<div> email: " + user.getEmail() + "</div>");
        writer.write("<div> password: " + user.getPassword() + "</div>");
        writer.write("<div>------------------------------------------</div>");
        writer.write("</body>");
        writer.write("</html>");
    }

    public void writeUserInfoHTML(HttpServletResponse resp, String message) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>");
        writer.write("<html lang='en'>");
        writer.write("<head>");
        writer.write("<meta charset='UTF-8'>");
        writer.write("<title>" + message  + "</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<div>" + message + "</div>");
        writer.write("</body>");
        writer.write("</html>");
    }
}
