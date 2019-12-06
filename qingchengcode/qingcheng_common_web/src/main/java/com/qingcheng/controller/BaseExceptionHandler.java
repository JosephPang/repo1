package com.qingcheng.controller;

import com.qingcheng.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/*
公共异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {

    //private Logger logger= LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(Exception.class)  //表示所有异常都经过这个方法
    @ResponseBody                       //将返回的结果转化为json
    public Result error(Exception e){
        e.printStackTrace();
        System.out.println("调用了异常处理");
        return new Result(1,e.getMessage());
    }

}
