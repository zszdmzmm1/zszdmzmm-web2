package day0901;

import day0818.JDBCConnection;
import day0818.User;
import day0818.UserLog;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener
public class CurrentLoginNumberListener implements HttpSessionAttributeListener {
    JDBCConnection jdbcConnection = JDBCConnection.getJDBCConnection();
    Connection connection = JDBCConnection.getConnection();
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String name = event.getName();
        if("user".equals(name)){
            ServletContext servletContext = event.getSession().getServletContext();
            int currentLoginNumber = (int) servletContext.getAttribute("totalNumberOfLoginUser");
            servletContext.setAttribute("totalNumberOfLoginUser", currentLoginNumber + 1);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String name = event.getName();
        if("user".equals(name)){
            Date date = new Date();
            User user = (User) event.getValue();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String sDate = simpleDateFormat.format(date);
            int userId = Integer.parseInt(user.getId().substring(1));
            UserLog userLog = new UserLog(userId, sDate, user.getRole() + "登出");
            jdbcConnection.addUserLog(connection, userLog);
            ServletContext servletContext = event.getSession().getServletContext();
            int currentLoginNumber = (int) servletContext.getAttribute("totalNumberOfLoginUser");
            servletContext.setAttribute("totalNumberOfLoginUser", currentLoginNumber - 1);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        this.attributeRemoved(event);
        this.attributeAdded(event);
    }
}
