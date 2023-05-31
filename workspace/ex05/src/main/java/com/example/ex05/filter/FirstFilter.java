package com.example.ex05.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class FirstFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FirstFilter에 요청이 지나간다");
        chain.doFilter(request,response);
//        FilterChain객체는 다음 필터에게 req,resp를 전달한다.
//        filter는 여러 개 사용이 가능하여 각각의 필터들이 연쇄적으로 실행된다.
//        그리고 FilterChain객체를 통해서 필터를 지나 Dispatcher Servlet에 도달할 수 있다.
//        그러므로 filter가 한 개일 때에도 FilterChain객체의 doFilter()를 반드시 사용해줘야 한다.
//        그렇지 않으면 사용자의 요청이 디스패쳐 서블릿에 전달되지 않아서 어플리케이션이 동작하지 못한다.
        System.out.println("FirstFilter에 응답이 지나간다");
    }

}
