package day0830;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Random;

public class SpecVerifyCode {
    public static final SecureRandom RANDOM = new SecureRandom();
    public static int[] code = new int[5];
    public static final int[][] COLOR = new int[][]{{0, 135, 255}, {51, 153, 51}, {255, 102, 102}, {255, 153, 0}, {153, 102, 0}, {153, 102, 153}, {51, 153, 153}, {102, 102, 255}, {0, 102, 204}, {204, 51, 51}, {0, 153, 204}, {0, 51, 102}};
    public static int num(int min, int max) {
        return min + RANDOM.nextInt(max - min);
    }

    public static int num(int num) {
        return RANDOM.nextInt(num);
    }
    public SpecVerifyCode() throws IOException {
        for (int i = 0; i < code.length; i++) {
            code[i] = num(10);
        }
    }

    public boolean out(OutputStream out) {
        return this.graphicsImage(code, out);
    }

    private boolean graphicsImage(int[] nums, OutputStream out) {
        try {
            BufferedImage bi = new BufferedImage(130, 48, 1);
            Graphics2D g2d = (Graphics2D) bi.getGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, 130, 48);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.drawOval(10, g2d);
            g2d.setStroke(new BasicStroke(2.0F, 0, 2));
            this.drawBesselLine(1, g2d);
            g2d.setFont(new Font("headache.ttf", 1, 32));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int fW = 130 / nums.length;
            int fSp = (fW - (int) fontMetrics.getStringBounds("W", g2d).getWidth()) / 2;
            for (int i = 0; i < nums.length; ++i) {
                g2d.setColor(this.color());
                int fY = 48 - (48 - (int) fontMetrics.getStringBounds(String.valueOf(nums[i]), g2d).getHeight() >> 1);
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

    public void drawBesselLine(int num, Graphics2D g) {
        for (int i = 0; i < num; ++i) {
            g.setColor(this.color());
            int x1 = 5;
            int y1 = num(5, 48 / 2);
            int x2 = 130 - 5;
            int y2 = num(48 / 2, 48 - 5);
            int ctrlx = num(130 / 4, 130 / 4 * 3);
            int ctrly = num(5, 48 - 5);
            int ctrlx1;
            if (num(2) == 0) {
                ctrlx1 = y1;
                y1 = y2;
                y2 = ctrlx1;
            }

            if (num(2) == 0) {
                QuadCurve2D shape = new QuadCurve2D.Double();
                shape.setCurve((double) x1, (double) y1, (double) ctrlx, (double) ctrly, (double) x2, (double) y2);
                g.draw(shape);
            } else {
                ctrlx1 = num(130 / 4, 130 / 4 * 3);
                int ctrly1 = num(5, 48 - 5);
                CubicCurve2D shape = new CubicCurve2D.Double((double) x1, (double) y1, (double) ctrlx, (double) ctrly, (double) ctrlx1, (double) ctrly1, (double) x2, (double) y2);
                g.draw(shape);
            }
        }

    }
    public void drawOval(int num, Graphics2D g) {
        for(int i = 0; i < num; ++i) {
            g.setColor(this.color());
            int w = 5 + (10);
            g.drawOval(num(130 - 25), num(48 - 15), w, w);
        }

    }

    protected Color color() {
        int[] color = COLOR[num(COLOR.length)];
        return new Color(color[0], color[1], color[2]);
    }

}
