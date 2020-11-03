package com.fpg.api.interceptor;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fpg.api.message.ReqBody;
import com.fpg.api.message.ReqHeader;
import com.fpg.api.message.Result;
import com.fpg.api.message.SecureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * @author fangyh
 * @version 1.0.0
 * @date 2020-11-2
 * @since 1.0.0
 */
@ControllerAdvice
public class BizRequestAdvice implements RequestBodyAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(BizRequestAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        //判断是否有此注解,只有为true时才会执行afterBodyRead
        return methodParameter.getParameterAnnotation(RequestBody.class) != null;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return new MappingJacksonInputMessage(inputMessage.getBody(), inputMessage.getHeaders());
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (Objects.isNull(body)) {
            return body;
        }
        LOGGER.info("input parameters:{}", body);

        if (!JSONUtil.isJson(String.valueOf(body))) {
            LOGGER.info("params {} is not json format.", body);
            throw new RuntimeException("params is not json format.");
        }

        Result result = JSONUtil.toBean(String.valueOf(body), Result.class);
        ReqHeader reqHeader = result.getReqHeader();
        ReqBody reqBody = result.getReqBody();

        //系统编码
        String systemId = reqHeader.getSystemId();
        //校验系统编码systemId是否有效

        //加密Key
        String securityKey = reqHeader.getSecurityKey();
        //校验加密Key是否为空

        //时间戳
        long timestamp = reqHeader.getTimestamp();
        //校验时间戳是否在5分钟内

        //随机字符串
        String nonce = reqHeader.getNonce();
        //校验随机字符串是否数据库中存在，存在表示重复请求

        //签名串=系统编码(systemId)、将参数密文(data)、AES密钥密文(securityKey)、时间戳(timestamp)、随机字符串(nonce)哈希计算
        String signData = reqHeader.getSignData();
        //校验签名串是否为空

        //参数密文
        String data = reqBody.getData();
        //校验参数密文是否为空

        //参数长度
        int dataSize = reqBody.getDataSize();

        //系统编码(systemId)、将参数密文(data)、AES密钥密文(securityKey)、时间戳(timestamp)、随机字符串(nonce)哈希计算
        List<String> names = List.of(systemId, data, securityKey, String.valueOf(timestamp), nonce);
        String signFields = String.join("!^", names);
        String verifiedSignData = cn.hutool.crypto.SecureUtil.sha256(signFields);
        if (!StrUtil.equals(signData, verifiedSignData)) {
            throw new RuntimeException("signData is incorrect");
        }

        //获取RSA私钥
        String priEncBase64 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANl42wfQVtsbmRr5rFmReB+/x9uuqTve9/PuRrRorxaN3HO3be4g+vQaQwZ5POLFCFjbMhyAFadYWCYhOmGjeOilczoS3uQAmkCHUYh7FusRJqCxQfIixZjqAhKmjPNArXyVKZrkqY+Av8KfBgw7OIDoJyDXHdBhxduScd/NmhqTAgMBAAECgYAIoYRH3hvkXEcKTAhsEZrr36el71umFaZPlxBVTqZMRdsO+KOzRLnRAE3SrvMcqOc455IokWjKmHX/JWh0wZElz9hvr4bZ+ktlrvlt0A5iF8RIXTK5IOMUFff3iX8WVJSMQdWyF6FYjQHeEgWZtVCX6+DX/+jq8vIw1d2cBeb5MQJBAO3Yu4gua4/E4TdG0i+y6SyIj2zOB0UvNeKzru2XQVZW7bnSoHAstvHzRftb7GKZHf5Rltd/vsPP60WspCmC4nkCQQDqEgbcuMlt2Agrz5SV+f2MMi6mFJx11BnlJeM75nTTtnBOy54Z/kFYIek2JsFeJ2J5KYGoIH58rgMxEdFLBIJrAkB1Tna3pMVyJBU8ERbrZZ8xUq0Cad8WFUHLbPmyvu3/qmIXSzXL/ZHBZU23uk+tFX3Ah/cQlDW/F9YfCBXzT1VpAkEAxEL/O/048ABOwaVY+fPWE8JT+O+ZTPbeC5QcFzKhzI7RVlBGrZbR5Xrxl3Uhd7fgSqVx0K4GQLaLeI5vBc6vJwJAevLjcoUPB9bNKD6qQTBqzJDbv148N72dEu5RlfkEguuRe4fb4QGTbqGwuIAR1t4K3noRNEzGYzaZwH/Ykzrd4g==";

        //用RSA私钥对AES密钥解密
        String aesKey = SecureUtil.decrypt4Rsa(Base64.decode(priEncBase64), securityKey);

        //用AES对数据解密获取原数据
        String plainTextData = SecureUtil.decrypt4Aes(Base64.decode(aesKey), data);
        if (dataSize != plainTextData.length()) {
            throw new RuntimeException("dataSize is incorrect");
        }
        LOGGER.info("plainText data is [{}]", plainTextData);
        //日志记入数据库
        return plainTextData;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }
}
