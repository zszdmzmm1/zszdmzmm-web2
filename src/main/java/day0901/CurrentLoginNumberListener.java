package day0901;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class CurrentLoginNumberListener implements HttpSessionAttributeListener {
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
            ServletContext servletContext = event.getSession().getServletContext();
            int currentLoginNumber = (int) servletContext.getAttribute("totalNumberOfLoginUser");
            servletContext.setAttribute("totalNumberOfLoginUser", currentLoginNumber - 1);
        }
    }
}
