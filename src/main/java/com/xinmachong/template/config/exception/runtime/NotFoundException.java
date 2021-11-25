package com.xinmachong.template.config.exception.runtime;

/**
 * @Author meyer@HongYe
 */
public class NotFoundException extends HttpException{

    public NotFoundException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}