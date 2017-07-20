/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yulei.core.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Richard Xue
 * @version 1.0
 * @date 06/21/2016
 * @description
 */
public class UserOperationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession httpSession = request.getSession();
//        String api = request.getRequestURI().replaceFirst(request.getContextPath()+BizConstants.API_PREFIX,"");
//        if(api.startsWith("/login") || api.startsWith("/logout")) {
//            return true;
//        }
//        UserDto userDto = (UserDto) httpSession.getAttribute(BizConstants.USER_INFO);
//        if(userDto == null){
//            throw new ServiceException(ServiceCode.AUTH_ERROR,"用户为空");
//        }
//        logBusiness.saveApiOperation(userDto.getUsername(),api);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
