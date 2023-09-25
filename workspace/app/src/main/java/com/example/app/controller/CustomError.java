package com.example.app.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomError implements ErrorController {

    @GetMapping("/error")
    public String error(HttpServletRequest req){
//        HTTP상태 코드를 req에게 받을 수 있다.
//        상태코드를 얻기위한 key는 우리가 외워서 쓸 수 없으므로 RequestDispatcher가 가진 상수를 활용한다.
        Object attribute =req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(attribute!=null){
            int statusCode = Integer.valueOf(attribute.toString());
            System.out.println(statusCode);
            if(statusCode== HttpStatus.NOT_FOUND.value()){
//                return new RedirectView("/error/404");
                return "error/404"; //404에러 : 요청 페이지를 찾을 수 없음
            }
        }
        return "error/500"; //404에러 : 요청 페이지를 찾을 수 없음
    }

}
