package com.fpg.api.ctrler;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class TestJasypt {
    public static void main(String[] args) {
        StandardPBEStringEncryptor standardPBEStringEncryptor =new StandardPBEStringEncryptor();
        /*配置文件中配置如下的算法*/
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        /*配置文件中配置的password*/
        standardPBEStringEncryptor.setPassword("EWRREWRERWECCCXC");
        /*要加密的文本*/
        String name = standardPBEStringEncryptor.encrypt("sa");
        String password =standardPBEStringEncryptor.encrypt("sa");
        /*将加密的文本写到配置文件中*/
        System.out.println("name="+name);
        System.out.println("password="+password);

        System.out.println(standardPBEStringEncryptor.decrypt("qxqb9tBz38NJsrUCv6rEbA=="));
        System.out.println(standardPBEStringEncryptor.decrypt("8IhkymtMwVwGs9wnxC3/cw=="));
    }
}
