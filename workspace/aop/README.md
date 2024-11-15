# 스프링 AOP 구현
## 스프링 AOP 구현5 - 어드바이스 순서
어드바이스는 기본적으로 순서를 보장하지 않는다. 순서를 지정하고 싶으면 `@Aspect`적용 위로   
`ort.springframework.core.annotation.@Order`애노테이션을 적용해야한다. 문제는 이것을 어드바이스 단위가 아니라 클래스 단위로 적용할 수 있다는 점이다.   
그래서 지금처럼 하나의 애프펙트에 여러 어드바이스가 있으면 순서를 보장 받을 수 없다. 따라서 **애스펙트를 별도의 클래스로 분리**해야한다.

## 스프링 AOP 구현6 - 어드바이스 종류
어드바이스는 앞서 살펴본 `@Around`외에도 여러가지 종류가 있다.

#### 어드바이스 종류
- `@Around`: 메서드 호출 전후에 수행, 가장 강력한 어드바이스,조인 인트 실행 여부 선택, 값 변환, 예외 변환 등이 가능
- `@Before`: 조인 포인트 실행 이전에 실행
- `@After Returning`: 조인 포인트가 정상 완료후 실행
- `@After Throwing`: 메서드가 예외를 던지는 경우 실행
- `@After`: 조인 포인트가 정상 또는 예외에 관계없이 실행(finally)

### 참고 정보 획득
모든 어드비이스는 `org.aspect.lang.JoinPoint`를 첫번째 파라미터에 사용할 수 있다.(생략해도 된다.)   
단 `@Around`는 `ProceedingJoinPoint`를 사용해야한다.

참고로 `ProceedingJoinPoint`는 `org.aspect.lang.JoinPoint`의 하위타입이다. 

### **JoinPoint** 인터페이스의 주요 기능
- `getArgs()`: 메서드 인수를 반환합니다.
- `getThis()`: 프록시 객체를 반환합니다.
-  `getTarget()`: 대상 객체를 반환합니다.
-  `getSignature()`: 조인되는 메서드에 대한 설명을 반환합니다.
-  `toString()`: 조인되는 방법에 대한 유용한 설명을 인쇄합니다.

### **ProceedingJoinPoint** 인터페이스의 주요 기능
- `proceed()`: 다음 어드바이스나 타겟을 호출한다.
