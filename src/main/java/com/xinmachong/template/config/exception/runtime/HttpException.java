package com.xinmachong.template.config.exception.runtime;

import lombok.Getter;

/**
 * @Author meyer@HongYe
 */
@Getter
public class HttpException extends RuntimeException {
    protected Integer code;
    protected String message;
}
