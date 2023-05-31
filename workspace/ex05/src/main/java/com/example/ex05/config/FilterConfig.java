package com.example.ex05.config;

import com.example.ex05.filter.FirstFilter;
import com.example.ex05.filter.SecondFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean //외부 라이브러리를 빈으로 등록할 때 붙여준다.
    public FilterRegistrationBean<FirstFilter> firstFilter(){
//        FilterRegistrationBean는 필터를 등록, 설정하는 클래스이다.
        FilterRegistrationBean<FirstFilter> registrationBean = new FilterRegistrationBean<>();

//        우리가 만든 FirstFilter를 등록한다.
        registrationBean.setFilter(new FirstFilter());
//        필터가 실행되야하는 URL패턴을 설정한다.
        registrationBean.addUrlPatterns("/user/*");
//        필터의 순서를 설정한다.
        registrationBean.setOrder(1);
//        필터의 이름을 설정한다.
//        필터의 이름을 개발자가 직접사용하지는 않지만 필터 사용 순서를 저장할 때 이름을 사용하므로 같이 설정해줘야 한다.
        registrationBean.setName("first");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<SecondFilter> secondFilter(){
        FilterRegistrationBean<SecondFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SecondFilter());
        registrationBean.addUrlPatterns("/user/*");
        registrationBean.setOrder(2);
        registrationBean.setName("second");
        return registrationBean;
    }




}
