package day0817;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;


@WebServlet("/img")
public class ResponseImgDownload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = this.getServletContext().getRealPath("./image/QQ图片20230720103937.jpg");
        FileInputStream fis = new FileInputStream(url);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("QQ图片20230720103937.jpg", "GBK"));
        OutputStream sos = resp.getOutputStream();
        IOUtils.copy(fis, sos);
        fis.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
