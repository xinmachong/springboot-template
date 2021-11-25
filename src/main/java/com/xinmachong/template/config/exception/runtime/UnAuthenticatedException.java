package com.xinmachong.template.config.exception.runtime;

/**
 * @Author meyer@HongYe
 */
public class UnAuthenticatedException extends HttpException{
    public UnAuthenticatedException(int code, String message){
        this.code = code;
        this.message = message;
    }
}
