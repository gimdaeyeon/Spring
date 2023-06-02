package com.example.ex06.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 클래스에 적용시키는 어노테이션은 ElementType.TYPE
// @Target({ElementType.METHOD,ElementType.TYPE}) 둘 다 적용하는 설정
@Target(ElementType.METHOD) //메소드에 적용시킬 어노테이션을 만든다는 의미
@Retention(RetentionPolicy.RUNTIME) //리텐션은 생명 주기를 의민한다.
//(어노테이션이 얼마나 유지되는지를 의민한다. 생명 주기가 끝난 시점부터는 제 기능을 못함)
public @interface LoggingPointCut {


}
