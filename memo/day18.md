## AOP(Aspect Oriented Programming)
### \- 관점 지향 프로그래밍을 의미하며 관점 지향은 로직을 핵심 관점, 부가 관점으로 나누어 분리하는 것을 지향하는 프로 그래밍 방법

- 횡단 관심사 : 공통되고 반복되는 관심사 (기능)   
예를 들어 예외 처리, 트랜잭션, 로깅, 보안 등

- 종단 관심사 : 핵심 로직


\- 관심사를 분리하면 개발자는 핵심 로직(종단 관심사)에만 집중하여 코드를 작성할 수 있으며 유지보수에 유리하다.

\- 분리한 기능을 관점(Aspect)라고 한다.

## Aop의 주요 용어
- Aspect(관점)   
  반복되는 기능인 횡단 관심사를 의미한다.   
  주로 포인트컷과 어드바이스로 구성된다.

- Advice   
  실제 부가 기능을 구현한 객체이다. (관점의 구현체)   
  어드바이스는 동작 시점에 따라 다양한 유형으로 구분한다.   
  1. @Before : 타겟 메소드 호출 전에 적용(서비스에 있는 메소드-> 메인 로직)
  2. @AfterReturning : 타겟 메소드가 실행 후 결과를 반환한 뒤에 적용
  3. @AfterThrowing : 타겟 메소드에서 예외 발생시 적용(try~[catch]~finally와 비슷)
  4. @After : 타겟 메소드에서 예외 발생 상관 없이 항상 적용(try~catch~[finally]와 비슷)
  5. @Around : 타겟 메소드 호출 전, 후로 개발자의 코드 순서에 따라 적용된다.(만능)

- JoinPoint   
  어드바이스를 적용시킬 수 있는 위치이다.   
  (우리의 경우 서비스 클래스의 메소드에 어드바이스를 적용시키게 된다. 즉, 서비스 클래스의 메소드들이 조인포인트이다.)

- PointCut   
  어드바이스가 적용될 조인포인트를 결정하는 표현식(전통적인 AspectJ문법)   
  (어느 메소드에 어드바이스를 적용 시킬지 결정하는 표현식)

  스프링부트 부터는 어노테이션으로 간편하게 처리가 가능하다(나중에 추가된 @AspectJ문법 )

- Target   
  어드바이스를 적용할 대상 객체를 의미한다.   
  주로 핵심 로직을 수행하는 클래스이다.

- Proxy (대리)   
  프록시는 가상의 객체를 의미한다.
  프록시는 2가지 방식으로 생성된다.
  1. 대상 객체가 인터페이스를 implements한 경우    
  프록시는 대상 객체와 동일한 인터페이스를 implements하여 만들어진다.

  2. 대상 객체가 인터페이스 없이 그냥 만들어진 경우   
  대상 객체의 클래스를 프록시가 상속받아 만들어진다.    
  프록시가 만들어지면 타겟 객체를 사용할 때 프록시 객체를 주입하게 된다.   
  프록시객체의 메소드가 실행되면 어드바이스(횡단관심사 코드)를 끼워 넣고 타겟의 메소드를 실행시킨다.

- Weaving   
  어드바이스를 핵심 로직에 적용하는 것을 위빙이라고 한다.   
  이 과정을 통해 횡단 관심사가 코드에 적용된다.

 

