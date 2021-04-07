package com.util;

import com.swetake.util.Qrcode;
import com.zhang.QRCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 加密、解密工具类
 * @data    2021/04/05
 * @author  zhangxinrun
 */
public class QRCodeUtil {

    /**
     * 加密：   文字信息 -> 二维码
     * @param contentInfo   隐藏的文字信息
     * @param imagePath     图片的路径
     * @param imageType     图片的类型
     * @param size
     */
    public void enCoderQRCode(String contentInfo, String imagePath, String imageType, int size) throws Exception {

        //在内存中生成图片
        BufferedImage renderedImage = qRcodeCommon(contentInfo, imageType, size);
        //文件路径
        File file = new File(imagePath);

        //生成图片
        ImageIO.write(renderedImage, imageType, file);
    }

    /**
     * 产生一个二维码的BufferedImage方法
     * @param contentInfo   二维码中隐藏的信息
     * @param imageType     图片格式
     * @param size      二维码边长
     * @return
     */
    private BufferedImage qRcodeCommon(String contentInfo, String imageType, int size) throws Exception {

        BufferedImage bufferedImage = null;

        //QRCode对象：字符串 -> boolean[][]
        Qrcode qrcode = new Qrcode();
        //设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
        qrcode.setQrcodeErrorCorrect('M');
        // N代表数字,A代表字符a-Z,B代表所有
        qrcode.setQrcodeEncodeMode('B');
        // 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大
        qrcode.setQrcodeVersion(size);

        //将字符串转换为byte数组
        byte[] bytes = contentInfo.getBytes();
        //字符串 --> boolean[][]
        boolean[][] codeOut = qrcode.calQrcode(bytes);

        int imageSize = 70 + 12 * (size - 1);

        bufferedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);

        //创建一个画板
        Graphics2D graphics2D = bufferedImage.createGraphics();

        //将画板的背景色设置为白色
        graphics2D.setBackground(Color.WHITE);
        //初始化
        graphics2D.clearRect(0, 0, imageSize, imageSize);
        //设置画板上图像的颜色（二维码的颜色）
        graphics2D.setColor(Color.BLACK);

        //坐标偏移
        int pixOff = 3;

        for (int i = 0; i < codeOut.length; i++) {
            for (int j = 0; j < codeOut.length; j++) {
                if(codeOut[i][j]){
                    //填充
                    graphics2D.fillRect(j * 3 + pixOff,i * 3 + pixOff, 3, 3);
                }
            }
        }

        graphics2D.dispose();
        bufferedImage.flush();

        return bufferedImage;
    }

}
