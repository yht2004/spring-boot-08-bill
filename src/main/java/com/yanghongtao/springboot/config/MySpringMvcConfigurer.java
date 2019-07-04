package com.yanghongtao.springboot.config;


import com.yanghongtao.springboot.component.MyLocaleResolver;
import com.yanghongtao.springboot.interceptor.LoginHanderInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySpringMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

            //添加属兔控制
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/index.html").setViewName("main/login");
                registry.addViewController("/main.html").setViewName("main/index");
            }

            //添加拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHanderInterceptor())
                        //拦截所有请求
                        .addPathPatterns("/**")
                        //排除不需要拦截的请求
                        .excludePathPatterns("/","/index.html","/login","/css/**","/img/**","/js/**");
            }
        };
       return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
