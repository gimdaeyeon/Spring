package com.example.ex06.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect //
@Component
@Slf4j
public class LoggingAspect {

//    execution(* com.example.ex06.service.UserService.*(..))은 포인트 컷이다.
//    포인트컷은 표현식이며, 어디에 어드바이스를 적용시킬지 나타낸다.
//    지금 적용한 포인트 컷은 UserService의 모든 메소드에 적용시키는 것이다.
//    (서비스까지 실행되는 경우에만 어드바이스가 실행된다.)
//    이러한 방식이 스프링프레임워크 부터 쓰이던 표현식을 사용한 포인트 컷이다.

//    첫 번째 *은 모든 리턴 타입을 의미한다.
//    만약 리턴타입이 void인 메소드에만 적용하고 싶다면
//    execution(void com.example.ex06.service.UserService.*(..)) 라고 작성하면 된다.

//    두 번째 *은 메소드의 이름이다. UserService클래스 안의 모든 메소드를 의미한다.
//    특정 메소드를 지정하고 싶다면 메소드 이름을 작성하면 된다.

//    (..)은 매개변수를 읨미한다. 매개변수 상과없이 모든 메소드에 적용하는 것을 의미한다.
//    execution(* com.example.ex06.service.UserService.*(String,int))
//    위의 표현식은 반환타입 상관없이 UserService클래스 내의 모든 메소드 중
//    매개변수에 String,int를 받는 메소드
    @Before("execution(* com.example.ex06.service.UserService.*(..))")
//    타겟으로 지정한 객체가 실행되기 직전에 적용시킬 어드바이스임을 나타낸다.
    public void logBefore(JoinPoint joinPoint){
        log.info("****** Before :" + joinPoint.getSignature().getName());
    }
//    JoinPoint는 어드바이스가 적용될 수 있는 위치를 의미한다.
//    매개변수의 JoinPoint는 어드바이스가 적용된 메소드의 정보를 가져온다.
//    getSignature() : 메소드의 시그니처를 가져온다.
//    시그니처란 메소드를 구분할 수 있는 고유한 정보를 의미한다.(선
//    언부와 비슷하다.)
//    그 정보에서 getName()을 통해 메소드의 이름만 가져올 수 있다.

    @After("execution(* com.example.ex06.service.UserService.*(..))")
    public void logAfter(JoinPoint joinPoint){
        log.info("=========After : "+ joinPoint.getSignature());
    }

    @Around("@annotation(com.example.ex06.aspect.LoggingPointCut)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint){
        log.info("*=*=*=*= around : "+proceedingJoinPoint.getSignature().getName());
//        ProceedingJoinPoint는 기존의 JoinPoint인터페이스를 확장시킨 인터페이스이다.
//        Around 어드바이스에서만 사용이 가능하다!!!
//        JoinPoint의 기능을 전부 가지고 있고 proceed() 메소드가 추가되었다.
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
//            proceed()는 이 어드바이스가 적용된 대상의 핵심 로직을실행시키는 메소드이다.
//            핵심 로직의 실행 위치에 따라 Around 어드바이스의 부가기능 실행 위치가 바뀐다.
//            어드바이스가 적용된 메소드가 반환타입이 존재한다면 해당 반화값을 여기사 받아 다시 반환해야한다.
        } catch (Throwable throwable) {
            throwable.printStackTrace();
//            Throwable은 Exception과 Error의 부모타입
        }

        log.info("========== around : "+proceedingJoinPoint.getSignature().getName());
        return result;
    }





}








