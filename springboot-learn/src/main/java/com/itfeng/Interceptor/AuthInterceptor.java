package com.itfeng.Interceptor;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义注解拦截器
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    public AuthInterceptor() {
        super();
    }

    //controller 调用之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截处理代码
        HandlerMethod method = (HandlerMethod) handler;
        LoginRequired loginRequired = method.getMethodAnnotation(LoginRequired.class);
        if (null != loginRequired) {
            //这个是需要拦截的方法
        } else {
            //这个是不需要拦截的方法
            return true;
        }
        //返回true通过，返回false拦截
        return true;
    }

    //controller 调用之后被调用，如果有异常则不调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    //controller 调用之后被调用，有没有异常都会被调用,Exception 参数里放着异常信息
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
