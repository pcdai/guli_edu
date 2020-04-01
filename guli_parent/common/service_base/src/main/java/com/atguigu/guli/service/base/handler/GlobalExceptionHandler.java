package com.atguigu.guli.service.base.handler;

import com.atguigu.common.base.result.R;
import com.atguigu.common.base.result.ResultCodeEnum;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author dpc
 * @Date 2020/4/1 16:28
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error();
    }

    @ResponseBody
    @ExceptionHandler(BadSqlGrammarException.class)
    public R error(BadSqlGrammarException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R error(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }
}
