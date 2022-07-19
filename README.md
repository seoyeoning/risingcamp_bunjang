# 개발일지

# 2022/07/16

---

- **기획서 작성 (세오, 룸큐, 오공)**
    
    [https://www.notion.so/softsquared/_E-037e799b55a24a8db76ace0d7b3cbb1c](https://www.notion.so/_E-037e799b55a24a8db76ace0d7b3cbb1c)
    

- **ERD 진행사항**
    - 80%
    - [https://aquerytool.com/aquerymain/index/?rurl=7c76d9b4-e07e-4339-96f4-f5d37e3f818d](https://aquerytool.com/aquerymain/index/?rurl=7c76d9b4-e07e-4339-96f4-f5d37e3f818d)
    - 비번: 0mr588

- **API 리스트업 - (7월 17일 예정)**

  

- **서버&클라이언트 회의**
    - 7월 17일까지
    - 서버: API 리스트업, ERD ,상품조회, 상품등록  API  서버반영
    - 클라이언트: 홈화면, 상품 조회, 상품 등록

# 2022/07/17

---

- **기획서 작성 (세오, 룸큐, 오공)**
    
    [https://www.notion.so/softsquared/_E-037e799b55a24a8db76ace0d7b3cbb1c](https://www.notion.so/_E-037e799b55a24a8db76ace0d7b3cbb1c)
    

- **ERD 진행사항**
    - 100% (수정 예정)
    - [https://aquerytool.com/aquerymain/index/?rurl=7c76d9b4-e07e-4339-96f4-f5d37e3f818d](https://aquerytool.com/aquerymain/index/?rurl=7c76d9b4-e07e-4339-96f4-f5d37e3f818d)
    - 비번: 0mr588

- **API 리스트업 - (7월 17일 예정)**
    - 50%

  

- **현재 어떤 기능(api)를 구현중에 있는지**
    - 상품 조회
    - 상품 상세페이지 조회
    - 상품 등록

- **더미데이터 진행상황**
    - 카테고리 1,2,3
    - 상품
    - 상품 이미지

- **서버&클라이언트 회의**
    - 7월 18일까지
    - 서버: API 리스트업 끝내기
    - 클라이언트: api 2~3개 연동하기
    


# 2022/07/18

---

- **기획서 작성 (세오, 룸큐, 오공)**
    
    [https://www.notion.so/softsquared/_E-037e799b55a24a8db76ace0d7b3cbb1c](https://www.notion.so/_E-037e799b55a24a8db76ace0d7b3cbb1c)
    

      

- **ERD 진행사항**
    - 100% (타입 수정, 테이블 컬럼 수정)
    - teg 테이블, location 테이블 추가 예정
    - [https://aquerytool.com/aquerymain/index/?rurl=7c76d9b4-e07e-4339-96f4-f5d37e3f818d](https://aquerytool.com/aquerymain/index/?rurl=7c76d9b4-e07e-4339-96f4-f5d37e3f818d)
    - 비번: 0mr588

- **API 리스트업**
    - 90% 수정사항 남아있음

  

- **현재 어떤 기능(api)를 구현중에 있는지**
    - 상품 조회
    - 상품 등록 (태그, 사진 등록, 카테고리, 지역 조회 )
    - 로그인

- **더미데이터 진행상황**
    - 카테고리 1,2,3
    - 상품
    - 상품 이미지

- **서버&클라이언트 회의**
    - 서버:  하루에 6개이상 만들기
    - 클라이언트:  api 연동하기
    

❗ **개발도중 이슈**

룸큐: 
1. 서브 도메인 prod로 spring boot 구축 실패
   a. 어떤 점이 어려웠는나: 기본 도메인에 적용은 해봤지만 서브도메인을 생성하여 적용해본적이 없어서 방법 자체가 헷갈렸다.
      잘못건드리면 다른것도 다 꼬여서 건드리는것도 쉽지 않았다.
 
   b. 현재 상황: 일단 기본 도메인에 연결하였다. api작성이 안정화되면 다시 시도할 예정

2. ec2에서 java를 빌드할 때 용량 문제로 계속 멈췄다.
    a. 해결 상황 : swap을 이용하여 용량을 늘려주었다.
        참고 사이트 : https://sundries-in-myidea.tistory.com/102

3. 메인 화면 상품 추천 api가 local에서는 성공하는데 server에서는 에러가 났다.
    a. 이슈 상황 9000번 포트와 관련된 ssl인증 문제 또는 프록시 문제일것으로 추정
    b. /nginx/sites-available/default 에서 여러가지 방법을 시도중인데 아직 해결하지 못했다..

세오: postman에서 서버와 연결할떄 ssl을 적용한 prod.도메인으로 들어가게 되면 오류가 뜨게됨

         브라우저에서는 ssl을 적용한 prod.도메인이 정상적으로 작동한다

         서버를 다시 구축하였지만 


여러번 리다이렉션 했다는 오류가 뜨며 해결하지 못하였음 재구축만 5번을 시도 하였지만 실패

# 2022/7/19

---

- 기획서 변동 사항
    - 상품 등록 API 구현 담당 변경  + 구현 시작 : 세오 → 룸큐
    - 일반 회원가입 API 구현 시작 (In progress) (담당 : 세오)
    - 일반 로그인 API 구현 시작 (In progress) (담당 : 세오)
    - 상품 상세페이지 API 수정사항 남아있어서 In progress 상태 유지
    
        → 수정 해야할 사항 : teg 여러개 가능, 지역, 몇분전에 올라왔는지, 배송지 포함 여부
    
    - 홈 화면 추천 상품 API  구현했는데 서버 연동 실패( local은 성공) In progress 상태 유지

- ERD 진행상황
    - 상품 등록 API 구현하기위해 등록, 등록 준비(장바구니 개념) , 사진, 카테고리, 태그 테이블 등 추가 예정
    - location 테이블 추가 예정
    - [https://aquerytool.com/aquerymain/index/?rurl=7c76d9b4-e07e-4339-96f4-f5d37e3f818d](https://aquerytool.com/aquerymain/index/?rurl=7c76d9b4-e07e-4339-96f4-f5d37e3f818d)
    - 비번: 0mr588
    
- **API 리스트업**
    - 90% 수정사항 남아있음
    
- **서버&클라이언트 회의**
    - 서버:  목요일까지 회원가입, 로그인, 상품 등록 완벽하게 구현
    - 클라이언트:  목요일까지 회원가입, 로그인, 상품 등록 회면 만들기
    - 목요일에 구현 테스트 예정

❗ **개발도중 이슈**

### 룸큐

번개장터는 기본 회원가입/로그인이 무조건 문자 인증이어서 외부 API를 이용한 문자 인증 시스템을 구현 하려다가 실패했다.

현재는 상품 등록 API 구현중이다.

상품 등록 과정에 굉장히 복잡해서 ERD와 datagrip 의 테이블들을 수정할 예정이고 계속 고민하고 있다.

- 상품 등록 과정 고민 사항
    - 사진 올릴때 갤러리 연동 등록전에 추가 삭제 자유롭게 가능 (따로 api 구현 예정)
    - 카테고리 3단계에 걸쳐서 선택해야함 (따로 api 구현 예정)
    - 태그 여러개 추가 가능해야함 (따로 api 구현 예정)
    - 지역 정하는건 나중에 시도할 예정
- 나중에 서버 수정하기
- 메인 화면 추천 상품 API 서버 연동 안되는 에러 해결하기

### 세오
