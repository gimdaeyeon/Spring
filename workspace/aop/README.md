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

# 스프링 AOP - 포인트컷
## 포인트컷 지시자
애스펙트J는 포인트컷을 편리하게 표현하기 위해 특별한 표현식을 제공한다.   
예) `@Pointcut("execution(* hello.aop.order..*(..))")`   
포인트컷 표현식은 AspejtJ pointcut expression 즉 애스펙트J가 제공하는 포인트컷 표현식을 줄여서 말하는 것이다.

### 포인트컷 지시자
포인트컷 표현식은 `execution`가틍ㄴ 포인트컷 지시자(Pointcut designator)로 시작한다. 줄여서PCD라 한다.

### 포인트컷 지시자의 종류
- `execution`: 메소드 실행 조인 포인트를 매칭한다. 스프링 AOP에서 가장 많이 사용하고, 기능도 복잡하다.
- `within`: 특정 타입 내의 조인포인트를 매칭한다.
- `args`: 인자가 주어진 타입의 인스턴스인 조인 포인트
- `this`: 스프링 빈 객체(스프링 AOP 프록시)를 대상으로 하는 조인 포인트
- `target`: Target 객체(스프링 AOP 프록시가 가르키는 실제 대상)를 대상으로 하는 조인 포인트
- `@warget`: 실행 객체의 클래스에 주어진 타입의 애노테이션이 있는 조인 포인트
- `@within`: 주어진 애노테이션이 있는 타입 내 조인 포인트
- `@annotation`: 메서드가 주어진 애노테이션을 가지고 있는 조인 포인트를 매칭
- `@args`: 전달된 실제 인수의 런타임 타입이 주어진 타입의 애노테이션을 갖는 조인 포인트
- `bean`: 스프링 전용 포인트컷 지시자, 빈의 이름으로 포인트컷을 지정한다.

# 스프링 AOP - 실무 주의사항

## 프록시와 내부 호출 - 문제

스프링은 프록시 방식의AOP를 사용한다.   
따라서 AOP를 적용하려면 항상 프록시를 통해서 해당 객체(Target)을 호출해야 한다.   
이렇게 해야 프록시에서 먼저 어드바이스를 호출하고, 이후에 대상 객체를 호출한다.   
만약 프록시를 거치지 않고 대상 객체를 직접 호출하게 되면 AOP각 적용되지 않고, 어드바이스도 호출되지 않는다.

AOP를 적용하면 스프링은 대상 객체 대신에 프록시를 스프링 빈으로 등록한다. 따라서 스프링은 의존관계 주입시에 항상 프록시 객체를 주입한다.   
프록시 객체가 주입되기 때문에 대상 객체를 직접 호출하는 문제는 일반적으로 발생하지 않는다. 하지만 대상 객체의 내부에서 메서드 호출이 발생하면 프록시를 거치지 않고
대상 객체를 직접 호출하는 문제가 발생한다.

## 프록시 기술과 한계 - 타입 캐스팅
JDK 동적 프록시와 CGLIB를 사용해서 AOP 프록시를 만드느 방법에는 각각 장단점이 있다.   
JDK 동적 프록시는 인터페이스가 필수이고, 인터페이슬 기반으로 프록시를 생성한다.   
CGLIB는 구체 클래스를 기반으로 프록시를 생성한다.

물론 인터페이스가 없고 구체 클래스만 있는 경우에는 CGLIB를 사용해야한다. 그런데 인터페이스가 있는 경우에는 JDK 동적프록시나 CGLIB 둘중에 하나를 선택할 수 있다.

스프링이 프록시를 만들때 제공하는 `ProsyFactory`에 `proxyTargetClass`옵션에 따라 둘중 하나를 선택해서 프록시를 만들 수 있다.

- `proxyTargetClass=false` JDK 동적 프록시를 사용해서 인터페이스 기반 프록시 생성
- `proxyTargetClass=true` CGLIB를 사용해서 구체 클래스 기반 프록시 생성
- 참고로 옵션과 무관하게 인터페이스가 없으면 JDK 동적 프록시를 적용할 수 없으므로 CGLIB를 사용한다.

### JDK 동적 프록시 한계
인터페이스 기반으로 프록시를 생성하는 JDK 동적 프록시는 구체 클래스로 타입 캐스팅이 불가능한 한계가 있다.

## 프록시 기술과ㅏ 한계 - CGLIB
스프링에서 CGLIB는 구체 클래스를 상속 받아서 AOP 프록시를 생성하 때 사용한다.   
CGLIB는 구체 클래스를 상속 받기 떄문에다음과 같은 문제가 있다.

### CGLIB 구체 클래스 기반 프록시 문제점
- 대상 클래스에 기본 생성자 필수
- 생성자 2번 호출 문제
- final 키워드 클래스, 메서드 사용 불가

#### 대상 클래스에 기본생성자 필수
CGLIB는 구체 클래스를 상속 받는다. 자바 언어에서 상소을 받으면 자식 클래스의 생성자를 호출할 때 자식 클래스의 생성자에서 부모클래스의 생성자도 호출해야 한다.(이 부분이 생략되어 있다면 자식 클래스의 생성자 첫줄에 부모 클래스의 기본생성자를 호출하는 `super()`가 자동으로 들어간다.)이 부분은 자바 문법 규약이다.   
CGLIB를 사용할 때 CGLIB가 만드는 프록시의 생성자는 우리가 호출하는 것이 아니다. CGLIB 프록시는 대상 클래스를 상속 받고,
생성자에서 대상 클래스의 기본 생성자를 호출한다. 따라서 대상 클래스에 기본 생성자를 만들어야 한다.

#### 생성자 2번 호출 문제
CGLIB는 구체클래스를 상속 받는다. 자바 언어데서 상속을 받으면 자식 클래스의 생성자를 호출할 때 부모 클래스의 생성자도 호출해야 한다. 그런데 왜 2번일까?
1. 실제 target의 객체를 생성할 때
2. 프록시 객체를 생성할 때 부모 클래스의 생성자 호출

#### final 키워드 클래스, 메서드 사용 불가
final 키워드가 클래스에 있으면 사속이 불가능하고, 메서드에 있으면 오버라이딩이 불가능하다. CGLIB는 상속을 기반으로 하기 때문에 두 경우 프록시가 생성되지 않거나 정상 동작하지 않는다.

프레임워크 같은 개발이 아니라 일반적인 웹 애프리케이션을 개발할 때는 `final`키워드를 잘 사용하지 않는다. 따라서 이 부분이 특별히 문제가 되지는 않는다.

#### 정리
JDK 동적 프록시는 대상 클래스 타입으로 주입할 때 문제가 있고, CGLIB는 대상 클래스에 기본 생성자 필수, 생성자 2번 호출 문제가 있다.

그렇다면 스프링은 어떤 방법을 권장할까?



## 프록시 기술과 한계 - 스프링의 해결책
스프링은 AOP 프록시 생성을 편리하게 제공하기 위해 오랜 시간 고민하고 문제들을 해결해왔다.

### 스프링의 기술 선택 변화
#### 스프링 3.2, CGLIB를 스프링 내부에 함께 패키징
CGLIB를 사용하려면 CGLIB 라이브러리가 별도로 필요했다. 스프링은 CGLIB 라이브러리를 스프링 내부에 함께 패키징해서 별도의 라이브러리 추가 없이 CGLIB를 사용할 수 있게 되었다. `CGLIB spring-core org.springframework`

#### CGLIB 기본 생성자 필수 문제 해결
스프링 4.0부터 CGLIB의 기본 생성자가 필수인 문제가 해결되었다.   
`objensis`라는 특별한 라이브러리를 사용해서 기본 생성자 없이 객체 생성이 가능하다.   
참고로 이 라이브러리는 생성자 호출 없이 객체를 생성할 수 있게 해준다.

#### 생성자 2번 호출 문제
스프링 4.0부터 CGLIB의 생성자 2번 호출 문제가 해결되었다.   
이것도 역시 `objensis`라는 특별한 라이브러리 덕분에 가능해졌다.   
이제 생성자가 1번만 호출된다.

#### 스프링 부트 2.0 CGLIB 기본 사용
스프링 부트 2.0 버전부터 CGLIB를 기본으로 사용하도록 했다.   
이렇게 해서 구체 클래스 타입으로 의존관계를 주입하는 문제를 해결했다.   
스프링 부트는 별도의 설정이 없다면 AOP를 적용할 때 기본적으로 `proxyTargetClass=true`로 설정해서 사용한다.   
따라서 인터페이스가 있어도 JDK 동적 프록시를 사용하느 것이 아니라 항상CGLIB를 사용해서 구체 클래스를 기반으로 프록시를 생성한다.   
물론 스프링은 우리에게 선택권을 열어주기 때문에 다음과 같이 설정하면 JDK 동적 프록시도 사용할 수 있다.   
`application.properties`
```properties
spring.aop.proxy-target-class=false
```
#### 정리
스프링은 최종적으로 스프링 부트 2.0에서 CGLIB를 기본으로 사용하도록 결정했다. CGLIB를 사용하면 JDK 동적 프록시에서 동작하지 않는 구체 클래스 주입이 가능하다.
여기에 추가로 CGLIB의 단점들이 이제는 많이 해결되었다. CGLIB의 남은 문제라면 `final`클래서나 메서드가 있는데, AOP를 적용할 대상에는 `final`클래스나 메서서드를 
잘 사용하지 않으므로 이 부분은 크게 문제가 되지는 않는다.

개발자 입장에서 보면 사실 어떤 프록시 기수을 사용하든 상관이 없다. JDK 동적 프록시든 CGLIB든 또는 어떤 새로운 프록시 기수을 사용해도 된다.
심지어 클라이언트입장에서 어떤 프록시 기술을 사용하는지 모르고 잘 동작하는 것이 가장 좋다. 단지 문제없고 개발하기에 편리하면 되는 것이다.









