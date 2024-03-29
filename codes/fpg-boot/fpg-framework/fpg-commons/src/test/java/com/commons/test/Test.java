/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.commons.test;


import com.ump.commons.util.QRCodeUtil;

/**
 * @author fangyh
 * @version 1.0.0
 * @date 2019-10-13 08:36:59
 * @since 1.0.0
 */
public class Test {
    private String name;

    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "https://github.com/yonghong915";
        // 嵌入二维码的图片路径
        String imgPath = "D:/qrCode/wife.jpg";
        // 生成的二维码的路径及名称
        String destPath = "D:/qrCode/qrcode/jam.jpg";
        // 生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);

    }

}
