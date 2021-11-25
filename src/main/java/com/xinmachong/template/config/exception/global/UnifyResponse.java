package com.xinmachong.template.config.exception.global;

import lombok.Data;

/**
 * @Author meyer@HongYe
 */
@Data
public class UnifyResponse {
    private Integer code;
    private String message;
    private Object data;

    public UnifyResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
