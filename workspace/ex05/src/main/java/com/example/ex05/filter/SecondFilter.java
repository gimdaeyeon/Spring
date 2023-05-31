package com.example.ex05.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

public class SecondFilter implements Filter {
    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        init은 initialize(초기화)의 약자이다.
//        즉, 필터를 생성할 때 실행되는 메소드이고 서버가 실행될 때 필더도 생성된다.
        System.out.println("SecondFilter init()!~!!");
        String encodingParam = filterConfig.getInitParameter("encoding");
        if(encodingParam!=null){
            encoding=encodingParam;
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("SecondFilter의 요청이 지나간다!!!!!");

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
        System.out.println("SecondFilter의 응답이 지나간다!!!!!");
    }

    @Override
    public void destroy() {
//        필터의 생명주기가 종료되면 실행된다(필터가 삭제되면)
        System.out.println("SecondFilter destroy()!!!!!!");
    }
}
