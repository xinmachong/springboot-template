package com.xinmachong.template.config.exception.runtime;

/**
 * @Author meyer@HongYe
 */
public class ParameterException extends HttpException{
    public ParameterException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
