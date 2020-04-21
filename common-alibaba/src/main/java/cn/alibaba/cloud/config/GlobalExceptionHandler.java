package cn.alibaba.cloud.config;


import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linz
 * @date 2019/10/31 15:38
 */
@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception ex) {
        String msg="";
        Map map= new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException) {
           msg= ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError().getDefaultMessage();
            map.put("result",msg);
         return   map;
        }

        map.put("result",ex.getMessage());
        return map;
    }


}


