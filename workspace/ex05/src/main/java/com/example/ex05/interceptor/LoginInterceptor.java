package com.example.ex05.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//인터셉터를 빈객체로 등록하여 스프링 컨테이너가 관리하게 만들 수 있다.
//이렇게 컨테이너가 관리하면 인터셉터 내부에 다른 빈객체를 주입받아 사용할 수 있다.
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        
        Object userNumber = session.getAttribute("userNumber");
        
        if(userNumber == null){
            response.sendRedirect("/user/login");
            return false;
        }
        
       return true;
    }
}
