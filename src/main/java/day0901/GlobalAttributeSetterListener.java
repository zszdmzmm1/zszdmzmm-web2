package day0901;

import day0904.DruidDemo;
import day0904.MybatisMapper;
import day0904.mybatis.mapper.UserMapper;
import day0905.IDb1Connect;
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
        IDb1Connect iDb1Connect = null;
        if(connector.equals("mybatis")){
            iDb1Connect = MybatisMapper.mapper;
        }else if(connector.equals("JDBC")){
            iDb1Connect = DruidDemo.getDruidDemo();
        }
        sce.getServletContext().setAttribute("connector", iDb1Connect);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
