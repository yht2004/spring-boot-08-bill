package com.yanghongtao.springboot.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHanderInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser != null){
            return true;
        }
        request.setAttribute("msg","无权限，请先登录");
        request.getRequestDispatcher("/index.html").forward(request,response);
        return false;
    }
}
