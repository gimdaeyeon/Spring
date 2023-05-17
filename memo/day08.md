# Unit Test(단위 테스트)
소스 코드 중 특정 모듈이 개발자가 의도한대로 작동하는지 검증하는 절차이다. (모듈크기가 정확히 정의되어 있지는 않다.)      
함수나 메소드의 테스트 케이스를 만드는 절차이며, 코드 변경으로 인한 문제가 발생한 경우 빠른 시간내에 이를 파악하고 수정할 수 있다.   
가장 이상적인 테스트 방법은 각 테스트 별로 서로 간섭하면 안되고, 항상 같은 결과를 기대할 수 있어야 한다.

## JUnit과 assert(검증)

### **Junit**
자바 어플리케이션의 단위 테스트를 쉽게 해주는 프레임 워크이다.   
assert메소드와 @Test, @Before, @After 등의 어노테이션을 지원한다.

### Assert 메소드
검증을 하기 위한 메소드이다.   
그러나 사용 방법이나 가독성이 떨어져서 잘 사용하지 않는다.

## **AssertJ 라이브러리(오픈 소스)**
AssertJ는 assertThat()으로 시작한다.
- assertThat(타겟).검증메소드().검증메소드()....   
검증 메소드는 이름만 봐도 기능을 알 정도로 직관적이기 때문에 사용 방법이 매우 간편하다.
- assertThatThrownBy(예외가 발생할것 같은 코드를 람다식으로 작성)   
 예외를 검증하는 메소드   
 isInstanceOf(예외객체의 클래스.class)
 hasMessageContaining("메세지")

---
## Mockito란?
mock을 만들고 mock의 행동을 정의하는 stubbing과 작동하는지에 대한 검증을 할 수 있는 verify등 다양한 기능을 제공해주는 프레임워크

### Junit에서 Mockito 사용하기
JUnit과 Mockito는 둘 다 프레임워크이기 때문에 서로 결합하여 사용하기 위해서는 추가 설정이 필요하다.   
JUnit5에서는 @ExtendWith(MockitoExtension.class)를 사용해야 기능이 확장된다.

### Mock객체란?
어떤 객체를 모방하는 가짜 객체이다. 껍데기만 있으며 내부 구현부는 존재하지 않는다.   
Mock객체를 만드는 것을 Mocking이라고 한다.

### Mockito에서 지원하는 어노테이션
- @Mock : Mock객체를 만들어준다.(Mocking)
- @InjectMocks : Mock객체를 주입해준다.

예시)   
관계 : UserService는 UserMapper를 주입받는다.   
사용 : UserService를 테스트하기 위해서는 UserMapper가 주입되어야하는데 UserMapper는 개발자가 테스트하고 싶은 대상이 아니다.   
그러므로 UserMapper를 Mock객체로 만들어 UserService에 주입하여 테스트를 진행한다. 
```java
@Mock //가짜를 만들고
    private UserMapper userMapper;

    @InjectMocks //가짜객체를 주입하겠다
    private UserService userService;
```

### 스터빙(stubbing)이란?
Mock객체의 메소드를 실행했을 때 행위를 미리 정의하는 것

### 스텁(stub)이란?
스터빙으로 새롭게 정의된 베소드

```java
when(userMapper.select()).thenReturn(userDto);
// 위의 코드는 stubbing을 하는것
// userMapper.select()
```

###  Mockito의 스터빙 방법
모키토의 스터빙 방법은 2가지가 있다.

1. OngoingStubbing 메소드   
 when()을 먼저 사용하고 뒤에 OngoingStubbing 메소드를 체이닝한다.   
 when()이 실행되었을 때 할 행위를 정하는 메소드이며 다음과 같이 사용한다.

    ```java
    when(mock객체.스터빙할 메소드()).OngoingStubbing()
    ```
    *특이사항* : when()의 매개변수로 반환타입이 void인 메소드를 사용할 수 없다.

     **OngoingStubbing 메소드**
    - thenReturn() : 스텁이 반환할 객체를 정의
    - thenThrown() : 스텁이 throw할 예외를 정의
    - thenCallRealMethod() : 스텁이 아닌 
    원본 객체의 메소드를 호출
    
<br>
    
2. Stubber 메소드   
  stubber메소드로 시작해서 중간에 when()이 들어간다.   
  when()이후에 스터빙할 메소드를 체이닝한다.   

      `stubber().when(mock객체).스터빙할 메소드()`

    특이사항 : 반환타입이 void인 메소드를 스터빙하려면 doNothing()을 사용한다.

    stubber메소드 종류   
    1. doReturn() : 스텁이 반환할 객체를 정으
    2. doThrow() : 스텁이 throw할 예외를 정의
    3. doNothing() : 스텁이 아무런 행위도 안하도록 정의(void메소드도 사용 가능)

## Verify(검증)
스텁 메소드를 검증하는 메소드이며 다음과 같이 사용한다.   
```java
verify(T mock객체, VerificationMode mode)   
``` 
VerificationMode는 검증할 값을 정의하는 메소드이다.    
주로 times(n)가 많이 사용된다.   

### VerificationMode의 종류
1. **times(n) : n번 호출됐는지 검증**\* 
2. never : 한 번도 호출하지 않았는지 검증
3. atLeastOne : 최소 한 번 호출됐는지 검증
4. atLeast(n) : 최소 n번 호출됐는지 검증

---
### 방법론 -> 테스트가 무지무지 중요하다!
설계 -> 개발 -> 테스트    
설계 ->  테스트 ->설계 ->  테스트 ->...->개발

---




