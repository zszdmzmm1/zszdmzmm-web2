package day0918.bobing.servlet;

import com.alibaba.fastjson.JSONObject;
import day0908.MessageDTO;
import day0918.bobing.pojo.Gift;
import day0918.bobing.service.BoBingServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/draw")
public class DrawServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoBingServiceImpl boBingService = BoBingServiceImpl.getInstance();
        MessageDTO messageDTO = boBingService.getResult();
        Gift gift = (Gift) messageDTO.getObject();
        JSONObject jsonObject = new JSONObject();
        if (gift == null) {
            jsonObject.put("gift", "对不起，您未获奖~");
        } else {
            jsonObject.put("gift", gift.toString());
        }
        jsonObject.put("result", messageDTO.getMessage());
        resp.getWriter().println(jsonObject);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
