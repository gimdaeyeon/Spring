# Filter
![Filter, Interceptor, aop](../resource/Filter,%20Interceptor,%20aop.png)

1. Filter   
  요청이 Dispatcher Servlet에 도달하기 전에 우선 처리한다.    
  가장 먼저 실행되기 때문에 이름에 맞게 요청과 응답을 걸러주는 필터 역할을 한다.   
  스프링 컨테이너 외부에 존재하므로 빈객체에 접근이 불가능하다.
  - init() : 필터 인스턴스 초기화 시점에 실행
  - doFilter() : 전, 후 처리
  - destroy() : 필터 인스턴스 종료 시점에 실행

---
# 스케쥴러
