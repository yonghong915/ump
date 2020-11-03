package com.fpg.api.interceptor;

import cn.hutool.core.codec.Base64;
import com.fpg.api.message.SecureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fangyh
 * @version 1.0.0
 * @date 2020-11-2
 * @since 1.0.0
 */
@Component
public class SignInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SignInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String appId  = request.getHeader("appId");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");


        String priEncBase64 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANl42wfQVtsbmRr5rFmReB+/x9uuqTve9/PuRrRorxaN3HO3be4g+vQaQwZ5POLFCFjbMhyAFadYWCYhOmGjeOilczoS3uQAmkCHUYh7FusRJqCxQfIixZjqAhKmjPNArXyVKZrkqY+Av8KfBgw7OIDoJyDXHdBhxduScd/NmhqTAgMBAAECgYAIoYRH3hvkXEcKTAhsEZrr36el71umFaZPlxBVTqZMRdsO+KOzRLnRAE3SrvMcqOc455IokWjKmHX/JWh0wZElz9hvr4bZ+ktlrvlt0A5iF8RIXTK5IOMUFff3iX8WVJSMQdWyF6FYjQHeEgWZtVCX6+DX/+jq8vIw1d2cBeb5MQJBAO3Yu4gua4/E4TdG0i+y6SyIj2zOB0UvNeKzru2XQVZW7bnSoHAstvHzRftb7GKZHf5Rltd/vsPP60WspCmC4nkCQQDqEgbcuMlt2Agrz5SV+f2MMi6mFJx11BnlJeM75nTTtnBOy54Z/kFYIek2JsFeJ2J5KYGoIH58rgMxEdFLBIJrAkB1Tna3pMVyJBU8ERbrZZ8xUq0Cad8WFUHLbPmyvu3/qmIXSzXL/ZHBZU23uk+tFX3Ah/cQlDW/F9YfCBXzT1VpAkEAxEL/O/048ABOwaVY+fPWE8JT+O+ZTPbeC5QcFzKhzI7RVlBGrZbR5Xrxl3Uhd7fgSqVx0K4GQLaLeI5vBc6vJwJAevLjcoUPB9bNKD6qQTBqzJDbv148N72dEu5RlfkEguuRe4fb4QGTbqGwuIAR1t4K3noRNEzGYzaZwH/Ykzrd4g==";
        //String decrypt = SecureUtil.decrypt4Rsa(Base64.decode(priEncBase64), encrypt);
        //将参数密文、AES密钥密文、时间戳、随机字符串哈希计算
        List<String> names = List.of(appId,timestamp,nonce);
        String signFields = String.join("!^",names);
        String hashMsg = cn.hutool.crypto.SecureUtil.sha256(signFields);


        return true;
    }
}
