package com.zhang.cooltwodimensionalcodecollection.util;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeOptions;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * @data 2021/04/08
 * @author xiaozhang
 */
public class QRCodeUtil {

    /**
     * 生成普通的二维码
     * @param text  上传的文本信息或者链接信息
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public static String normal(String text) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text).asString();
    }

    /**
     * 生成带有logo的二维码
     * @param text  上传的文本或链接
     * @param logoFile  上传的logo图
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public static String logo(String text, InputStream logoFile) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text).setLogo(logoFile)
                .setLogoRate(7)
                .setLogoStyle(QrCodeOptions.LogoStyle.ROUND)
                .asString();
    }

    /**
     * 生成彩色的二维码
     * @param text  上传的文本信息或者链接
     * @return
     * @throws IOException
     * @throws WriterException
     */
        public static String color(String text) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setDrawPreColor(Color.RED)
                .asString();
    }

    /**
     * 生成带有背景图的二维码
     * @param text  上传的文本信息或链接
     * @param backgroundFile    背景图的图片
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public static String background(String text, InputStream backgroundFile) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setBgImg(backgroundFile)
//                .setBgStyle(QrCodeOptions.BgImgStyle.PENETRATE)
//                .setBgH(500)
//                .setBgW(500)
//                .setH(500)
//                .setW(500)
                .setBgOpacity(0.5f)
                .asString();
    }

    /**
     * 生成特殊形状的二维码
     * @param text  上传的文本或链接
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public static String style(String text) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setBgH(500)
                .setBgW(500)
                .setH(500)
                .setW(500)
                .setDrawEnableScale(true)
                .setDrawStyle(QrCodeOptions.DrawStyle.CIRCLE)
                .asString();
    }

    /**
     * 生成图片填充的二维码
     * @param text  上传的文本信息或链接
     * @param backgroundFile    上传的填充图片
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public static String imageFill(String text, InputStream backgroundFile) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setH(500)
                .setW(500)
                .setDrawEnableScale(true)
                .setErrorCorrection(ErrorCorrectionLevel.H)
                .setDrawStyle(QrCodeOptions.DrawStyle.IMAGE)
                .addImg(1, 1, backgroundFile)
                .asString();
    }

    /**
     * 生成gif动态图的二维码
     * @param text  扫描二维码显示文本或者跳转网页链接
     * @param backgroundFile    上传的gif图
     * @return
     * @throws IOException
     * @throws WriterException
     */
    public static String gif(String text, InputStream backgroundFile) throws IOException, WriterException {
        return QrCodeGenWrapper.of(text)
                .setH(500)
                .setW(500)
                .setBgImg(backgroundFile)
                .setBgOpacity(0.5f)
                .setPicType("gif")
                .asString();
    }
}
