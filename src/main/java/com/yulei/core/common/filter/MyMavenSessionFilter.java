package com.yulei.core.common.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yulei on 2016/5/12.
 */
public class MyMavenSessionFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyMavenSessionFilter.class);
    /** 登录界面 */
    protected String loginPage = "/api/login";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("init--->");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String api = httpServletRequest.getRequestURI().replaceFirst(httpServletRequest.getContextPath(),"");
        // 静态资源不进行拦截
        // || api.startsWith("/html")
        if(api.equals("/") || api.startsWith(loginPage) || api.startsWith("/css") || api.startsWith("/js") || api.startsWith("/images")  || api.startsWith("/lib")) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        LOGGER.debug("doFilter ---> {}", api);
        //Object userInSession = httpServletRequest.getSession().getAttribute(BizConstants.USER_INFO);
        //if(userInSession != null){
        //    chain.doFilter(httpServletRequest,httpServletResponse);
        //} else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        //}
    }

    @Override
    public void destroy() {
        LOGGER.info("destroy--->");
    }
}
