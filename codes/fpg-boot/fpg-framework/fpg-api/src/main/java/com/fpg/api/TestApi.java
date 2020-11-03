package com.fpg.api;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.*;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONUtil;
import com.fpg.api.message.ReqBody;
import com.fpg.api.message.ReqHeader;
import com.fpg.api.message.Result;
import com.fpg.api.message.SecureUtil;
import com.ump.commons.web.ResultRsp;
import com.ump.commons.web.ResultUtil;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author fangyh
 * @version 1.0.0
 * @date 2020-11-2
 * @since 1.0.0
 */
public class TestApi {
    public static final String BASE_CHAR_NUMBER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String getRandom(int length) {
        Random random = RandomUtil.getRandom(true);
        char[] chars = BASE_CHAR_NUMBER.toCharArray();
        char[] saltChars = new char[length];
        for (int i = 0; i < length; i++) {
            int n = random.nextInt(BASE_CHAR_NUMBER.length());
            saltChars[i] = chars[n];
        }
        return new String(saltChars);
    }

    public static void main(String[] args) {
        TestApi test = new TestApi();
        String nonce = getRandom(16);
        String securityKey = getRandom(16);
        long timestamp = System.currentTimeMillis();
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setSystemId("pcms");
        reqHeader.setTimestamp(timestamp);
        reqHeader.setNonce(nonce);

        ReqBody reqBody = new ReqBody();

        Map<String,String> conP = new HashMap<>();
        conP.put("aaa","1111");
        conP.put("bbb","2222");
        ResultRsp rsp = ResultUtil.success("返回成功",conP);

        String content = JSONUtil.toJsonStr(rsp);
        reqBody.setDataSize(content.length());


        //2.AES密钥
        byte[] key = SecureUtil.getAesKey();
        String aesKey = Base64.encode(key);
        System.out.println(aesKey);

        //3.用AES对参数加密
        String encryptHex = SecureUtil.encrypt4Aes(key, content);
        System.out.println("encryptHex="+encryptHex);


        //4.生成RSA密钥对
        //KeyPair keyPair = SecureUtil.getRsaKeyPair();


        //String pubEncBase64 = SecureUtil.getPublicKey4Rsa(keyPair);
        //String priEncBase64 = SecureUtil.getPrivateKey4Rsa(keyPair);

        //System.out.println("pubEncBase64=" + pubEncBase64);
        //System.out.println("priEncBase64=" + priEncBase64);
        //5.用RSA公钥对AES密钥加密

        String pubEncBase64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZeNsH0FbbG5ka+axZkXgfv8fbrqk73vfz7ka0aK8Wjdxzt23uIPr0GkMGeTzixQhY2zIcgBWnWFgmITpho3jopXM6Et7kAJpAh1GIexbrESagsUHyIsWY6gISpozzQK18lSma5KmPgL/CnwYMOziA6Ccg1x3QYcXbknHfzZoakwIDAQAB";
        String encryptAesKey = SecureUtil.encrypt4Rsa(Base64.decode(pubEncBase64), aesKey);

        String retMsg = content;//JSONUtil.parse(content).toStringPretty();
        //System.out.println(retMsg);



        String systemId = "pcms";
        //6.系统编码(systemId)、将参数密文(retMsg)、AES密钥密文(encryptAesKey)、时间戳(timestamp)、随机字符串(nonce)哈希计算
        List<String> names = List.of(systemId,encryptHex,encryptAesKey,String.valueOf(timestamp),nonce);
        String signFields = String.join("!^",names);
        String hashMsg = cn.hutool.crypto.SecureUtil.sha256(signFields);

        System.out.println("hashMsg=" + hashMsg);

        //1.构造参数数据
        reqBody.setData(encryptHex);

        Result rlt = new Result();

        reqHeader.setGlobalId(UUID.randomUUID(true).toString(true));
        reqHeader.setSecurityKey(encryptAesKey);
        reqHeader.setSignData(hashMsg);

        rlt.setReqHeader(reqHeader);
        rlt.setReqBody(reqBody);

        System.out.println(JSONUtil.parse(rlt).toStringPretty());
        //7.将参数密文、AES密钥密文、时间戳、随机字符串、签名值发送服务端
        System.out.println("encryptHex=" + encryptHex + " encryptAesKey=" + encryptAesKey + " timestamp=" + timestamp + " nonce=" + nonce + " hashMsg=" + hashMsg);

        String priEncBase64 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANl42wfQVtsbmRr5rFmReB+/x9uuqTve9/PuRrRorxaN3HO3be4g+vQaQwZ5POLFCFjbMhyAFadYWCYhOmGjeOilczoS3uQAmkCHUYh7FusRJqCxQfIixZjqAhKmjPNArXyVKZrkqY+Av8KfBgw7OIDoJyDXHdBhxduScd/NmhqTAgMBAAECgYAIoYRH3hvkXEcKTAhsEZrr36el71umFaZPlxBVTqZMRdsO+KOzRLnRAE3SrvMcqOc455IokWjKmHX/JWh0wZElz9hvr4bZ+ktlrvlt0A5iF8RIXTK5IOMUFff3iX8WVJSMQdWyF6FYjQHeEgWZtVCX6+DX/+jq8vIw1d2cBeb5MQJBAO3Yu4gua4/E4TdG0i+y6SyIj2zOB0UvNeKzru2XQVZW7bnSoHAstvHzRftb7GKZHf5Rltd/vsPP60WspCmC4nkCQQDqEgbcuMlt2Agrz5SV+f2MMi6mFJx11BnlJeM75nTTtnBOy54Z/kFYIek2JsFeJ2J5KYGoIH58rgMxEdFLBIJrAkB1Tna3pMVyJBU8ERbrZZ8xUq0Cad8WFUHLbPmyvu3/qmIXSzXL/ZHBZU23uk+tFX3Ah/cQlDW/F9YfCBXzT1VpAkEAxEL/O/048ABOwaVY+fPWE8JT+O+ZTPbeC5QcFzKhzI7RVlBGrZbR5Xrxl3Uhd7fgSqVx0K4GQLaLeI5vBc6vJwJAevLjcoUPB9bNKD6qQTBqzJDbv148N72dEu5RlfkEguuRe4fb4QGTbqGwuIAR1t4K3noRNEzGYzaZwH/Ykzrd4g==";
        //RSA rsaDe = new RSA(priEncBase64, null);
        // byte[] decrypt = rsaDe.decrypt(encrypt, KeyType.PrivateKey);

        String decrypt = SecureUtil.decrypt4Rsa(Base64.decode(priEncBase64), encryptAesKey);
        System.out.println(decrypt);


        String desStr = SecureUtil.decrypt4Aes(Base64.decode(decrypt), encryptHex);
        System.out.println("desStr=" + desStr);



    }
}
