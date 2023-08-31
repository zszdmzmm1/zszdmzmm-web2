package day0831;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter("/MyFilterServlet")
public class MyFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String parameter = req.getParameter("key1");
        req.setAttribute("key1", parameter + "-update");
        chain.doFilter(req, res);
        Cookie cookie = new Cookie("key", "value");
        res.addCookie(cookie);
    }
}