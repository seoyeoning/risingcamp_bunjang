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
 
   b. 

세오: postman에서 서버와 연결할떄 ssl을 적용한 prod.도메인으로 들어가게 되면 오류가 뜨게됨

         브라우저에서는 ssl을 적용한 prod.도메인이 정상적으로 작동한다

         서버를 다시 구축하였지만 


여러번 리다이렉션 했다는 오류가 뜨며 해결하지 못하였음 재구축만 5번을 시도 하였지만 실패
