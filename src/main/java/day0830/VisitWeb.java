package day0830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class VisitWeb {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:8080/zszdmzmm_web2_war/ShowCookieServlet");
        URLConnection uc = url.openConnection();
/*        uc.setConnectTimeout(1000);
        uc.setReadTimeout(2000);*/
        uc.addRequestProperty("Cookie", "key=value; Max-Age=60");
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        while((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
        }
        in.close();
    }
}
