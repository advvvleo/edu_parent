package com.la.eduservice.exceptionhandler;

import com.la.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@EnableAutoConfiguration
@ControllerAdvice
@Slf4j
public class GlobalExceptionHabdler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }
    //自定义异常
    @ExceptionHandler(ExceptionEntity.class)
    @ResponseBody
    public R error(ExceptionEntity e){
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
