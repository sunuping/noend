package com.sunup.noend.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证图
 */
public class VerificationImage {
    private static final int width = 160;
    private static final int height = 40;
    //干扰线长度
    private static final int lineSize = 30;
    //随机产生字符个数
    private static final int stringNum = 4;
    private static final String randomString = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private static final Random random = new Random();
    public static final String SESSION_KEY = "verificationdiagram";

    /**
     * 获取字体
     *
     * @return
     */
    private static Font getFont() {
        return new Font("Times New Roman", Font.ROMAN_BASELINE, 40);
    }

    /**
     * 获取颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandomColor(int fc, int bc) {
        fc = Math.min(fc, 255);
        bc = Math.min(bc, 255);

        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 12);

        return new Color(r, g, b);
    }

    /**
     * 绘制干扰线
     *
     * @param g
     */
    private static void drawLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(20);
        int yl = random.nextInt(10);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机字符串
     *
     * @param num
     * @return
     */
    private static String getRandomString(int num) {
        num = num > 0 ? num : randomString.length();
        return String.valueOf(randomString.charAt(random.nextInt(num)));
    }

    /**
     * 绘制字符串
     *
     * @param g
     * @param randomString
     * @param i
     * @return
     */
    private static String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(getRandomColor(108, 190));
        System.out.println(randomString.length());
        String rand = getRandomString(random.nextInt(randomString.length()==0?1:randomString.length()));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(6));
        g.drawString(rand, 40 * i + 10, 25);
        return randomString;
    }

    public static void getRandomCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //BufferedImage 具有缓冲区的Image 用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setColor(getRandomColor(105, 189));
        g.setFont(getFont());

        //绘制干扰线
        for (int i = 0; i < lineSize; i++) {
            drawLine(g);
        }

        //绘制随机字符串
        String random_string = "";
        for (int i = 0; i < stringNum; i++) {
            random_string = drawString(g, random_string, i);
        }

        System.out.println(random_string);

        g.dispose();

        session.removeAttribute(SESSION_KEY);
        session.setAttribute(SESSION_KEY, random_string);

        String base64Str = "";
        ImageIO.write(image, "PNG", response.getOutputStream());
    }

}
