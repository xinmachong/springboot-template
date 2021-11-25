package com.xinmachong.template.config.exception.runtime;

/**
 * @Author meyer@HongYe
 */
public class ForbiddenException extends HttpException{

    public ForbiddenException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
