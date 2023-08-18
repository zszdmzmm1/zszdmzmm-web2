package day0817;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;


@WebServlet("/img")
public class ResponseImg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = this.getServletContext().getRealPath("./image/QQ图片20230720103937.jpg");
        BufferedImage buffImg = ImageIO.read(new FileInputStream(url));
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("QQ图片20230720103937.jpg", "GBK"));
        OutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
