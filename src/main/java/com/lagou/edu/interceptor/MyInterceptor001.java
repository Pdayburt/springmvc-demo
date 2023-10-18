package com.lagou.edu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义SpringMVC拦截器
 */
public class MyInterceptor001 implements HandlerInterceptor {

    /**
     * 业务方法执行之前执行
     * 这里往往完成权限的校验
     * @param request
     * @param response
     * @param handler
     * @return 返回值boolean 是否放行？ true 放行 false 终止
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor001----->preHandle");
        return true;
    }

    /**
     *  业务方法执行完成但没有跳转页面
     * @param request
     * @param response
     * @param handler
     * @param modelAndView 封装了视图和数据，此时尚未跳转页面呢，可以在这里针对返回的数据和视图信息进行修改
     * @throws Exception
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor001----->postHandle");
    }

    /**
     * 页面已经跳转渲染完毕之后执行
     * @param request
     * @param response
     * @param handler
     * @param ex 这里可以捕获异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor001----->afterCompletion");

    }

}
