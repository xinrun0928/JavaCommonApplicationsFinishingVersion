package com.zhang;

import com.util.QRCodeUtil;

/**
 * 生成二维码
 * @date 2021/04/05
 * @author zhangxinrun
 */
public class QRCode {
    public static void main(String[] args) throws Exception {
        /**
         * 生成图片的路径      src/二维码.png
         * 文字信息、网址信息        "Hello World"
         */

        //生成的图片路径
        String imagePath = "D:\\二维码.png";
        //需要生成的文字信息、网址信息
        String contentInfo = "xiao xiao zhang";

        /**
         * 加密：  文字信息 -> 二维码
         * 解密：  二维码 -> 文字信息
         */

        QRCodeUtil qrCodeUtil = new QRCodeUtil();
        //加密
        qrCodeUtil.enCoderQRCode(contentInfo,imagePath,"png",8);
        //解密
    }
}
