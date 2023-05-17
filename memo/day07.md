## Rest

- 컨트롤러 클래스에서 @RestController어노테이션 붙여주면 Rest를 사용하는 컨트롤러가 생성된다.
- 기존의 컨트롤러와 사용방법은 똑같다.
- url주소는 보통 예를들어 기존 유저를 다루던 컨트롤러에서 "/user/\*"를 -> "/users/v1(버전)/\*"이런식으로 사용한다 (꼭 지킬필요는 없음) 
- return을 하면 그 내용이 화면으로 전달된다.

---
## url주소의 일부를 변수처럼 활용하기( ex - github주소)

@GetMapping("/{boardNumber}")    
 이런식으로 mapping url안에 {}를 쓰고 그 안에 넘겨줄 받을 값을 적는다.  
매개변수에서 받을 값에 @PathVariable("boardNumber") 이런식으로 어떤값을 받아올지 지정한다.
 ```java
 @GetMapping("/{boardNumber}")
public BoardDto boardDetail(@PathVariable("boardNumber") Long boardNumber){
      
     BoardDto boardDto = boardService.getBoardInfo(boardNumber);

        return boardDto;
    }
 ```
---
## Assertj
Assertion라이브러리의 불편한 점을 보완해서 업그레이드한 라이브러리 테스트코드 확인을 편리하게 할 수 있게 도와주는 라이브러리

### 선언방법
1. Assertions.~         
  ex) `Assertions.assertThat(userDto.getUserName()).isEqualTo("홍길동")`      
  이렇게 사용할 경우 매번 사용할 때 마다 Assertions를 선언해야한다는 불편함이 있다. 때문에 방법 2를 주로 사용한다.


2. 클래스 상단 import부분에 `import static org.assertj.core.api.Assertions.*;` 를 붙여넣는다         
  (Assertions안의 모든 메소드를 static메소드처럼 사용하겠다.        
  -> Assertions를 붙이지 않고 바로 메소드를 불러와 사용한다.)

### 사용하는 메소드들

- `assertThat(param)` : 검증하고자하는 값을 매개변수에 넣는다.7
- `isEqualTo(param)` : 선언한 값과 매개변수의 갑이 일치하는지 비교한다.
- `isNotEmpty()` : 비어있는지 확인
- `contains("반갑")` : 포함되어있는지 확인
- `startsWith("안") `: 해당 글자로 시작하는지 확인
- `isNotNull() `: null이 아닌지 확인
- `isInstanceOf(IllegalArgumentException.class)` : 해당 객체의 타입과 같은지 확인
- `hasMessage("존재하지 않는 회원입니다.") `: 가지고있는 예외메세지가 매개변수와 일치하는지 확인