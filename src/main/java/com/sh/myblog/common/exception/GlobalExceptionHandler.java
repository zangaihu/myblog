package com.sh.myblog.common.exception;

import com.sh.myblog.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By Sunhu At 2020/5/4 22:24
 *
 * @author Sun
 * 全局异常捕获
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest request,Exception e){

        log.error("-------------->捕获全局异常",e);

        if (e instanceof NullPointerException)
        {
            //do something
        }

        ModelAndView mav=new ModelAndView();
        mav.addObject("exception",e);
        mav.addObject("message",e.getMessage());
        mav.addObject("url",request.getRequestURL());
        mav.setViewName("error");

        return mav;
    }

    @ExceptionHandler(value = HwException.class)
    @ResponseBody
    public Result jsonErrorHandler(HttpServletRequest req, HwException e) {
        return Result.fail(e.getMessage(), "some error data");
    }




}
