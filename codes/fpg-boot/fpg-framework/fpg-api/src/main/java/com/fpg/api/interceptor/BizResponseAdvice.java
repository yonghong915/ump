package com.fpg.api.interceptor;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.fpg.api.message.ReqBody;
import com.fpg.api.message.ReqHeader;
import com.fpg.api.message.Result;
import com.fpg.api.message.SecureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * @author fangyh
 * @version 1.0.0
 * @date 2020-11-2
 * @since 1.0.0
 */
@RestControllerAdvice
public class BizResponseAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BizResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        LOGGER.info("output parameters:{}", body);

        //获取随机字符串
        String nonce = SecureUtil.getRandom(SecureUtil.RANDOM_LEN);

        //时间戳
        long timestamp = System.currentTimeMillis();

        //获取AES密钥
        byte[] aesKey = SecureUtil.getAesKey();
        String securityKey = Base64.encode(aesKey);

        //用AES对参数加密
        String encryptParams = SecureUtil.encrypt4Aes(aesKey, JSONUtil.toJsonStr(body));
        LOGGER.info("encryptParams={}", encryptParams);

        //获取RSA公钥
        String pubEncBase64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZeNsH0FbbG5ka+axZkXgfv8fbrqk73vfz7ka0aK8Wjdxzt23uIPr0GkMGeTzixQhY2zIcgBWnWFgmITpho3jopXM6Et7kAJpAh1GIexbrESagsUHyIsWY6gISpozzQK18lSma5KmPgL/CnwYMOziA6Ccg1x3QYcXbknHfzZoakwIDAQAB";

        //用RSA公钥对AES密码加密
        String encryptAesKey = SecureUtil.encrypt4Rsa(Base64.decode(pubEncBase64), securityKey);

        String systemId = "pcms";
        // 系统编码(systemId)、将参数密文(encryptParams)、AES密钥密文(encryptAesKey)、时间戳(timestamp)、随机字符串(nonce)哈希计算
        List<String> names = List.of(systemId, encryptParams, encryptAesKey, String.valueOf(timestamp), nonce);
        String signFields = String.join("!^", names);
        String signData = cn.hutool.crypto.SecureUtil.sha256(signFields);
        LOGGER.info("signData={}", signData);

        //构造返回参数数据
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setSystemId(systemId);
        reqHeader.setTimestamp(timestamp);
        reqHeader.setNonce(nonce);
        reqHeader.setGlobalId(UUID.randomUUID(true).toString(true));
        reqHeader.setSecurityKey(encryptAesKey);
        reqHeader.setSignData(signData);

        ReqBody reqBody = new ReqBody();
        reqBody.setDataSize(String.valueOf(body).length());
        reqBody.setData(encryptParams);

        Result rlt = new Result();
        rlt.setReqHeader(reqHeader);
        rlt.setReqBody(reqBody);

        String retStr = JSONUtil.toJsonPrettyStr(rlt);
        LOGGER.info("result:{}",retStr);
        if(body instanceof  String){
            return retStr;
        }
        return rlt;
    }
}
