package day0821;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/result")
public class RandomResult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("""
                <!doctype html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport"
                          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                    <meta http-equiv="X-UA-Compatible" content="ie=edge">
                    <title>随机选择</title>
                </head>
                <body>
                        <div>
                            <img src="image/""" + req.getAttribute("theOne") + """
                            "height="150" width="150" alt="选中头像"/>
                        </div>                            
                </body>
                </html>""");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
