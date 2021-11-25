package com.xinmachong.template.config.exception.runtime;

/**
 * @Author meyer@HongYe
 */
public class ServerErrorException extends HttpException {
    public ServerErrorException(int code, String message){
        this.code = code;
        this.message = message;
    }
}
