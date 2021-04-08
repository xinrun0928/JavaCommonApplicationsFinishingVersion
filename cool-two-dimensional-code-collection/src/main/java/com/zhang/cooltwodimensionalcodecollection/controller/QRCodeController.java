package com.zhang.cooltwodimensionalcodecollection.controller;

import com.zhang.cooltwodimensionalcodecollection.util.QRCodeUtil;
import com.zhang.cooltwodimensionalcodecollection.util.ZxingUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @data 2021/04/08
 * @author xiaozhang
 */
@RestController
public class QRCodeController {

    /**
     *  二维码
     * @param file  获取上传的图片
     * @param text  获取的文本信息
     * @param flag  生成何种类型的二维码
     * @return
     */
    @RequestMapping("/create")
    public String createQrcode(@RequestParam(value = "logoFile", required = false) MultipartFile file,
                               @RequestParam(value = "text") String text,
                               @RequestParam(value = "flag") String flag) throws Exception {
        try{
            if(file == null){
                if("normal".equals(flag)){  //生成普通二维码
//                return ZxingUtil.createImage(text, null);
                    return QRCodeUtil.normal(text);
                }else if ("color".equals(flag)){    //生成彩色二维码
                    return QRCodeUtil.color(text);
                }else if ("style".equals(flag)){    //生成特殊形状的二维码
                    return QRCodeUtil.style(text);
                }
            }else {
                if("normal".equals(flag)){  //生成普通二维码
//                return ZxingUtil.createImage(text, file.getInputStream());
                    return QRCodeUtil.logo(text, file.getInputStream());
                }else if("background".equals(flag)){    //生成带背景图的二维码
                    return QRCodeUtil.background(text, file.getInputStream());
                }else if("imageFill".equals(flag)){     //生成图片填充的二维码
                    return QRCodeUtil.imageFill(text, file.getInputStream());
                }else if("gif".equals(flag)){   //生成gif二维码
                    return QRCodeUtil.gif(text, file.getInputStream());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "xiaozhang";
    }
}
