/*
 * Copyright (C) 2016 YuWei. All rights reserved.
 * You can get our information at http://www.zhixindu.com
 * Anyone can't use this file without our permission.
 */
package com.yulei.core.app;

import com.yulei.core.common.interceptor.UserOperationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * @author yulei
 * @version 1.0
 * @date 03/12/2016
 * @description
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.yulei.core.controller"})
public class WebMvcConfig /*extends WebMvcConfigurerAdapter */{

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userOperationInterceptor());
    }

    public UserOperationInterceptor userOperationInterceptor() {
        return new UserOperationInterceptor();
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8),new MediaType("text", "html", UTF8),new MediaType("text", "xml", UTF8)));
        converters.add(stringConverter);
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new FormHttpMessageConverter());
    }

    public ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean() {
        ContentNegotiationManagerFactoryBean bean = new ContentNegotiationManagerFactoryBean();
        bean.setFavorPathExtension(false);
        return bean;
    }

    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        return bean;
    }

    public CommonsMultipartResolver getMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver  = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(204800000);
        return commonsMultipartResolver;
    }

    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:/i18n/messages");
        resource.setDefaultEncoding("UTF-8");
        resource.setUseCodeAsDefaultMessage(true);
        return resource;
    }
}
