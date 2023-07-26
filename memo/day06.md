## 동적쿼리
MyBatis 프레임워크에서 지원하는 강력한 기능이다.
쿼리를 상황에 따라 동적으로 생성해주어 하나의 쿼리를 여러 상황에서 사용할 수 있게 해준다.

`<if test="조건"><if>`

  조건을 확인하고 해당 조건이 true인 경우에만 태그 내부의 SQL코드를 삽입한다.
  - 조건에 문자열을 비교한느 경우 작은 따옴표로 감싸주고 equals()를 사용한다.
  - 비교연산자는 ==, !=, > ,< 등등 사용 가능
  - null검사도 가능하다. ( `memberId != null`)
  - 여러 조건은 and, or로 묶을 수 있다. `(searchType !=null and searchType.equals('boardTitle'))`
  - 부정 연산자 (!) 사용도 가능하다

```xml
<choose> ~ <when> ~ <otherwise>

  <choose>
    <when test="조건"> </when> 
    <when test="조건"> </when> 
    .....
    <otherwise></otherwise>
  </choose>
```

` <where> `
    태그 내부의 조건절을 생성해주는 태그이다.
    주로 where태그 내부에 if태그 처럼 동적으로 쿼리를 삽입하는 태그를 사용한다  

```xml
<when>
  <if test ="조건"></if>
  <if test ="조건"></if>
  <if test ="조건"></if>
</when>
```

 내부의 if문에서 하나라도 true가 나오면 where절을 추가해주고 if문에서 모두 false가 나오면 where절을 생략한다.

`<trim>`
       동적쿼리를 생성할 때 접두어(prefix), 접미어 (suffix)를 붙여주고 지워주는 기능을 가진 태그이다. `<if>`의 부족한 점을 보완해주는 역할을 한다. 

```xml
 <trim 속성1 속성2 ....>
      <if test="조건"></if>
      <if test="조건"></if>
      ....
   </trim>
```
- 속성의 종류     
  1. prefix : trim태그 내부의 쿼리 앞에 붙는 명령어를 지정한다.
  2. suffix : trim태그 내부의 쿼리 마지막 붙는 명령어를 지정한다.
  3. prefixOverrides : trim태그 내부의 쿼리 앞에 명령어 중 지우고 싶은 것을 지정한다.
  4. suffixOverrides : trim태그 내부의 쿼리 마지막에 명령어 중 지우고 싶은 것을 지정한다.


---
# Optional\<T>

옵셔널 클래스는 NPE를 방어하기 위해 사용한다.
개발을 할 때 가장 많이 발생하는 예외 중 하나가 NPE이다. 참조변수에 들어있는 값이 null인경우 접근 연산자(.)를 이용하여 메소드나 필드에 접근하면 NPE가 발생하므로 프로그램이 강종될 수 있다.
그러므로 if문을 이용하여 해당 값이 null인지 검사를 해야하는데 null이 나올 수 있는 경우의 수가 너무 많다보니 if문을 너무 많이 사용하여 개발을 피곤하게 만든다. 

Optional타입은 null 체크를 보다 간결하고 안전하게 코드를 작성하도록 도와준다.


---
# Optional 객체 생성하기

1. `empty() `
```java
    Optional<T> opt = Optional.empty();
```
    비어있는 옵셔널 객체를 생성한다.
    
2. `of()`
    ```java
    Optional<T> opt = Optional.of(값);
    ```
    값을 저장하고 있는 옵셔널 객체를 생성한다. 값이 확실하게 null이 아닌경우에만 사용한다.
    만약 null을 저장하면 NPE가 발생한다.
    
3. `ofNullable()`
    ```java
    Optional<T> opt = Optional.ofNullable(값);
    ```
    
    값을 저장하고 있는 옵셔널 객체를 생성한다.  값이 null일 수도 있는 경우 사용한다. 
    

옵셔널 객체는 값을 저장할 수 있으며 저장한 값이 null인지 체크하거나, null이면 다른 값으로 대체해서 반환하는 등의 메소드를 지원한다.

# Optional 객체의 메소드

1. `get()`
    
   ` opt.get()`
    
    opt객체에 저장된 값을 반환한다. null인 경우 예외가 발생된다. (NoSuchElementException)
    
    ``` java
    Optional<String> opt = Optional.of("test");
    String result = opt.get();
    ```
    
    
2. `orElse()`
    
    `opt.orElse(대체 값)`
    
    opt객체에 저장된 값을 반환한다. 만약 null인 경우 [대체 값]이 반환된다.
    
```java
    Optional<String> opt = Optional.of("test");
    String result = opt.orElse("a");
    
    System.out.println(isABC(null));
    
    public boolean isABC(String str){
    // return str.equals("ABC"); //str이 null인 경우 오류 발생함
       return Optional.ofNullable(str)
       .orElse("null이네").equals("ABC");
    }
```
    

    
3. `orElseGet()`
    
    opt.orElseGet(람다식) → opt.orElseGet(MyClass::new)
    
    opt객체에 저장된 값을 반환한다. 만약 null인 경우 [람다식의 결과]가 반환된다.
```java
    class Student{
       String name;
    
       //getter
    }
    Student std = null;
    String name = Optional.ofNullable(std).orElseGet(Student::new).getName();
```


    
4. `orElseThrow()`
    
    opt.orElseThrow(특정 예외)  → opt.orElseThrow(NullPointerException::new)
    
    opt객체에 저장된 값을 반환한다. 만약 null인 경우 [특정 예외]가 발생된다.
    
``` java
    Optional.ofNullable(null).orElseThrow(NullPointerException::new);
```

    
5. `isPresent()`
    
    opt.isPresent()
    
    opt객체에 저장된 값이 null이면 false, null이 아니면 true를 반환한다.(조건식으로 사용)
    
6. `opt.ifPresent(람다식)`
    
    opt객체에 저장된 값이 null이 아닌 경우에만 람다식을 실행한다.
    

이 외에도 `Stream<T>`에서 사용하는 `map()` , `filter()` 등을 사용할 수 있다.

    



