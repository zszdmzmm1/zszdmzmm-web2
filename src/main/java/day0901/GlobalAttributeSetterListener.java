package day0901;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class GlobalAttributeSetterListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("totalNumberOfLoginUser", 0);
        sce.getServletContext().setAttribute("totalNumberOfOnlineVisitor", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
