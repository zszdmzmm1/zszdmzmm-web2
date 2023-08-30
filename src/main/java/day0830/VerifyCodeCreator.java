package day0830;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyCodeCreator {
    int[] code = new int[5];
    public VerifyCodeCreator() throws IOException {
        Random random = new Random();
        for (int i = 0; i < code.length; i++){
            code[i] = random.nextInt(10);
        }
    }
    public boolean out(OutputStream out) {
        return this.graphicsImage(code, out);
    }

    private boolean graphicsImage(int[] nums, OutputStream out) {
        try {
            BufferedImage bi = new BufferedImage(130, 48, 1);
            Graphics2D g2d = (Graphics2D)bi.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int fW = 130 / nums.length;
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int fSp = (fW - (int)fontMetrics.getStringBounds("W", g2d).getWidth()) / 2;
            for(int i = 0; i < nums.length; ++i) {
                int fY = 48 - (48 - (int)fontMetrics.getStringBounds(String.valueOf(nums[i]), g2d).getHeight() >> 1);
                g2d.drawString(String.valueOf(nums[i]), i * fW + fSp + 3, fY - 3);
            }
            g2d.dispose();
            ImageIO.write(bi, "png", out);
            out.flush();
        } catch (IOException var18) {
            var18.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }
        return false;
    }
}
