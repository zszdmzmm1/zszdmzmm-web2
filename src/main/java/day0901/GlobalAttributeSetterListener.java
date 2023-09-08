package day0901;

import day0904.UserDaoJDBCImpl;
import day0904.UserDaoMybatisImpl;
import day0905.UserDao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class GlobalAttributeSetterListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("totalNumberOfLoginUser", 0);
        sce.getServletContext().setAttribute("totalNumberOfOnlineVisitor", 0);
        String connector = sce.getServletContext().getInitParameter("connector");
        UserDao userDao = null;
        if(connector.equals("mybatis")){
            userDao = UserDaoMybatisImpl.mapper;
        }else if(connector.equals("JDBC")){
            userDao = UserDaoJDBCImpl.getUserDaoJDBC();
        }
        sce.getServletContext().setAttribute("connector", userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
