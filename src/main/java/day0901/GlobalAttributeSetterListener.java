package day0901;

import day0904.DruidDemo;
import day0904.MybatisMapper;
import day0905.IUserDau;
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
        IUserDau iUserDau = null;
        if(connector.equals("mybatis")){
            iUserDau = MybatisMapper.mapper;
        }else if(connector.equals("JDBC")){
            iUserDau = DruidDemo.getDruidDemo();
        }
        sce.getServletContext().setAttribute("connector", iUserDau);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
