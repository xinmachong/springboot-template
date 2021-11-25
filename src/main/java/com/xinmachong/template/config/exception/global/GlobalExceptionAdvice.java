package com.xinmachong.template.config.exception.global;

import com.xinmachong.template.config.exception.runtime.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author meyer@HongYe
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest request, HttpException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return new UnifyResponse(500,"服务器异常",method + " " + requestUrl);
    }

    @ExceptionHandler(value = HttpException.class)
    public UnifyResponse handleHttpException(HttpServletRequest request,HttpException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return new UnifyResponse(e.getCode(),e.getMessage(),method + " " + requestUrl);
    }


    /**
     * 处理参数为空时服务返回的警告信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public UnifyResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder(bindingResult.getFieldErrors().size() * 16);
        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            if (i > 0) {
                errorMessage.append(",");
            }
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            errorMessage.append(fieldError.getField());
            errorMessage.append(":");
            errorMessage.append(fieldError.getDefaultMessage());
        }
        return new UnifyResponse(400,errorMessage.toString(),0);
    }
}
