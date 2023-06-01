package com.example.ex05.config;

import com.example.ex05.interceptor.ExecutionTimeInterceptor;
import com.example.ex05.interceptor.LoginInterceptor;
import com.example.ex05.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// WebMvcConfigurer 인터페이스는 스프링 MVC 웹 설정을 정의할 수 있는 인터페이스이다.
// 인터셉터 설정을 위해 필요하다.
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    private final ExecutionTimeInterceptor executionTimeInterceptor;

    @Autowired
    public InterceptorConfig(LoginInterceptor loginInterceptor, ExecutionTimeInterceptor executionTimeInterceptor) {
        this.loginInterceptor = loginInterceptor;
        this.executionTimeInterceptor = executionTimeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistry객체는 인터셉터를 등록 및 관리하는 객체이다.

//        경로 설정 시 /*과 /**의 차이
//        /* 은 하위 경로 1개 /user/login
//        /** 은 하위 경로 2개 이상 /user/v1/login
        registry.addInterceptor(new MyInterceptor())    //우리가 만든 인터셉터를 등록한다.
                .addPathPatterns("/user/**")            // URL패턴을 등록하여 특정 URL패턴에서 인터셉터가 실행되도록 설정한다.
                .excludePathPatterns("/user/login")    // 특정 URL에서는 등록한 인터셉터가 실행되지 않도록 제외시킨다.
                .order(1);

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/board/**")
                .excludePathPatterns("/board/list")
                .order(2);

        registry.addInterceptor(executionTimeInterceptor)
                .addPathPatterns("/**")
                .order(Ordered.HIGHEST_PRECEDENCE); //최우선 순위로 만들어준다.
    }
}










