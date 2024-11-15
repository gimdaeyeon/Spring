package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    //    hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){}// pointcut signature (메서드 이름과 파라미터를 합쳐서 포인트컷 시그니처라 한다.)

    //    클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}

//    allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}

}
