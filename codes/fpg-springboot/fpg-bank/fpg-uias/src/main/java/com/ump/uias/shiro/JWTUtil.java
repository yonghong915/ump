package com.ump.uias.shiro;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

/**
 * JWT-JSON Web Token
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
public class JWTUtil {
	/**
	 * 过期时间30分钟
	 */
	private static final long EXPIRE_TIME = 30 * 60 * 1000;

	/**
	 * 校验token是否正确
	 *
	 * @param token  密钥
	 * @param secret 用户的密码
	 * @return 是否正确
	 */
	public static boolean verify(String token, String username, String secret) {
		try {
			// 根据密码生成JWT效验器
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
			// 效验TOKEN
			verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 获得token中的信息无需secret解密也能获得
	 *
	 * @return token中包含的用户名
	 */
	public static String getUsername(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("username").asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * 生成签名,30min后过期
	 *
	 * @param username 用户名
	 * @param secret   用户的密码
	 * @return 加密的token
	 */
	public static String sign(String username, String secret) {
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		// 附带username信息
		return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);

	}

	public static void main(String[] args) {
		String username = "fangyh";
		String secret = "e10adc3949ba59abbe56e057f20f883e" + "{1234567890abcde}";
		String token = sign(username, secret);
		System.out.println("token=" + token);
		String us = getUsername(
				"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NTY5MzkwMzMsInVzZXJuYW1lIjoiZmFuZ3loIn0.gpINB3k2Rsq1CpUZqimwmrkrScJazyy5DimGrbCvBH8");
		System.out.println("us=" + us);
		// System.out.println(verify(token, username, secret));
	}
}
