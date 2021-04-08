package com.zhang.cooltwodimensionalcodecollection.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @data 2021/04/08
 * @author xiaozhang
 */
public class ZxingUtil {

    //设置字符集
    private final static String CHARSET = "utf-8";

    /**
     * 创建二维码
     * @param text  上传的文本
     * @param imgPath   上传的图片
     * @return
     * @throws Exception
     */
    public static String createImage(String text, InputStream imgPath) throws Exception{
        HashMap map = new HashMap();
        // 二维码的纠错度
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 二维码支持编码，字符集
        map.put(EncodeHintType.CHARACTER_SET, CHARSET);
        // 二维码的边距
        map.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 300, 300, map);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        if(imgPath == null){
            //生成普通二维码
            return imageToBase64(image);
        }
        //生成带logo的二维码
        image = insertLogo(image, imgPath);
        return imageToBase64(image);
    }

    /**
     * 生成带logo的二维码
     * @param image
     * @param imgPath
     * @return
     */
    private static BufferedImage insertLogo(BufferedImage image, InputStream imgPath) throws IOException {
        Image logo = ImageIO.read(imgPath);
        int width = logo.getWidth(null);
        int height = logo.getHeight(null);
        if(width > 100){
            width = 100;
        }
        if(height > 100){
            height = 100;
        }

        Image logoImage = logo.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(logoImage, 0, 0, null);
        graphics.dispose();

        Graphics2D graphics2D = image.createGraphics();
        int x = (300 - width) / 2;
        int y = (300 - height) / 2;
        graphics2D.drawImage(logoImage, x, y, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, height, 6, 6);//圆角矩形
        graphics2D.setStroke(new BasicStroke(3f));//设置画笔粗细
        graphics2D.draw(shape);
        graphics2D.dispose();

        return image;
    }

    /**
     * BufferedImage转换为Base64对象
     */
    public static String imageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", arrayOutputStream);
        return Base64.encodeBase64String(arrayOutputStream.toByteArray());
    }

}
