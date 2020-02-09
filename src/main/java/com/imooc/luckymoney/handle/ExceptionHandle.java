package com.imooc.luckymoney.handle;

import com.imooc.luckymoney.entity.Result;
import com.imooc.luckymoney.exception.MoneyException;
import com.imooc.luckymoney.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 捕获到异常
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof MoneyException){
            MoneyException moneyException = (MoneyException) e;
            return ResultUtil.error(moneyException.getCode(),moneyException.getMessage());
        }
        logger.error("系统异常 {}",e);
        return ResultUtil.error(-1,"未知错误");
    }
}
