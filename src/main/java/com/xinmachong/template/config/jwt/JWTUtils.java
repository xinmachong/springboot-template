package com.xinmachong.template.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @Author meyer@HongYe
 */
@Component
public class JWTUtils {

    private static int expire;
    private static String signature;

    // 此处需要注意构造函数和@Autowired自动注入先后顺序问题
    @Autowired
    public JWTUtils(JWTProperties jwtProperties) {
        JWTUtils.expire = jwtProperties.getExpire();
        JWTUtils.signature = jwtProperties.getSignature();
    }

    /**
     * 生成 token
     */
    public static String getToken(Map<String,String> map) {
        Date date = new Date(System.currentTimeMillis() + expire);
        Algorithm algorithm = Algorithm.HMAC256(signature);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        // 附带username信息
        return builder
                //到期时间
                .withExpiresAt(date)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * 校验 token 是否正确
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(signature)).build().verify(token);
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
