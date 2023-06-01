package com.example.ex05.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class ExecutionTimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long endTime =System.currentTimeMillis();
        long startTime = (Long)request.getAttribute("startTime");
        long processingTime = endTime-startTime;
        System.out.println("처리 시간 : "+ processingTime/1000.0+"s");// 컨트롤러의 처리시간
        request.setAttribute("processingTime",processingTime);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime =System.currentTimeMillis();
        long startTime = (Long)request.getAttribute("startTime");
        long totalTime = endTime-startTime;

//        컨트롤러 처리와 view렌더링까지 최종적인 결과를 만들어내는데를걸린 시간
        System.out.println("최종 시간 : "+ totalTime/1000.0+"s");
    }
}
