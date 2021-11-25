package com.xinmachong.template.config.jwt;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xinmachong.template.config.exception.runtime.ParameterException;
import com.xinmachong.template.config.exception.runtime.ServerErrorException;
import com.xinmachong.template.config.exception.runtime.UnAuthenticatedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author meyer@HongYe
 * 对未放行的请求进行令牌校验
 */
@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中令牌
        String token = request.getHeader("token");
        try {
            JWTUtils.verify(token);
            return true;
        }catch (SignatureVerificationException e){
            throw new UnAuthenticatedException(401,"无效签名");
        }catch (TokenExpiredException e){
            throw new UnAuthenticatedException(401,"token过期");
        }catch (AlgorithmMismatchException e){
            throw new ParameterException(400,"token算法不一致");
        }catch (Exception e){
            throw new ServerErrorException(500,"服务判定异常");
        }
    }
}
