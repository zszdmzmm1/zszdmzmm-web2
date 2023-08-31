package day0831;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FilenameFilter;
import java.io.IOException;


@WebFilter(filterName = "SecondFilter")
public class SecondFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("SecondFilter.doFilter");
        chain.doFilter(req, res);
        System.out.println("SecondFilter.doFilter ---after");
    }
}
