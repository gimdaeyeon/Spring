package com.example.ex05.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        preHandle() : 컨트롤러의 핸들러 메소드가 실행되기 전에 preHandle() 메소드가 실행된다.
//        true를 반환하면 요청처리를 진행하고
//        false를 반환하면 요청처리를 중지한다.(컨트롤러에 요청이 도달하지 못함)
        System.out.println("preHandle() 실행!!!!!!");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        postHandle() : 컨트롤러의 핸들러 메소드가 실행된 후, view페이지를 만들기 전에 실행된다.
//        핸들러 메소드를 거친 후이기 때문에 모델, 객체를 조작하거나 view 설정을 바꿀 수 있다.
        System.out.println("postHandle() 실행!!!!");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        afterCompletion() : view 페이지가 생성된 후 == 요청 처리가 완료된 후 실행된다.
        System.out.println("afterCompletion 실행!~~!!!");
    }
}
