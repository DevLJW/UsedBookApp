<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->







<!-- ABOUT THE PROJECT -->
## 중고도서 커뮤니티App
![중고도서 커뮤니티](https://github.com/user-attachments/assets/ab051dc5-4757-4c31-80bf-c5d66d83fd20)
<br>


  
## 개발동기
대학생활을 하면서 매학기가 바뀌면 사야하는 교재,참고도서 등 적지 않은 금액이 발생하고,<br>
학년이 올라가면서 전에 쓰던 책들을 처분하고 싶어, 판매자와 구매자들 간의<br>
저렴한 가격에 판매 또는 구매 했으면 좋겠다는 생각에 기획 했습니다.
<br>
<br>

## 개발기간
2022.10.01 ~ 2022.12.01
<br>
<br>


## 역할:
* 1인개발
* 프론트엔드
* 백엔드
* 데이터 베이스 설계
<br>




## 기술 스택
### FrontEnd
<div>
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white">
<img src="https://img.shields.io/badge/JetpackCompose-4285F4?style=for-the-badge&logo=JetpackCompose&logoColor=white">
</div>

### BackEnd&DateBase
<div>
<img src="https://img.shields.io/badge/FireBase-DD2C00?style=for-the-badge&logo=FireBase&logoColor=white">
</div>



### API
<div>
<img src="https://img.shields.io/badge/Retrofit-E10098?style=for-the-badge&logo=GraphQL&logoColor=black">
</div>

<br>
<br>

## 주요기능소개
### 1. 회원가입
 <img src="https://github.com/user-attachments/assets/0d6785ea-239f-4046-ba9a-0b5c90e37fcf" width="50%" height="40%">
 <br><br><br>
회원가입 필드는, 이메일,패스워드,패스워드 확인,닉네임란으로 구성이 되어 있습니다.<br>
회원가입 성공 시, FireBase의 SignWithEmailAndPassword 메소드를 호출하여 DB에 저장시킵니다.<br>
회원가입 실패 시, 실패한 내용이 입력한 하단에 출력 됩니다.
<br><br>



### 2. 로그인
 <img src="https://github.com/user-attachments/assets/67b313c5-551b-4a56-a872-35088ade7d75" width="40%" height="20%">
  <br><br><br>
로그인 필드는, 이메일,패스워드,패스워드 확인,닉네임란으로 구성이 되어 있습니다.<br>
입력한 데이터가 DB의 데이터와 일치하면, 로그인이 성공 합니다.<br>
로그인 성공 시, 사용자에게 토큰을 부여하며, 부여받은 AccessToken을 통해 인가를 받게 됩니다.<br>
로그인 실패 시, 실패한 내용이 입력한 하단에 출력 됩니다.
<br><br>



### 3. 유저정보슬라이드
 <img src="https://github.com/user-attachments/assets/6476fcc9-1288-4985-b753-891c05fd7931" width="40%" height="20%">
  <br><br><br>
유저정보슬라이드는 회원정보수정,회원탈퇴,로그아웃 버튼으로 구성이 되어 있습니다.<br>
회원정보수정,회원탈퇴,로그아웃 버튼 클릭 시, 토큰에 대한 인가를 진행 합니다.<br>
인가 성공 시, 사용자에게 토큰을 부여하며, 부여받은 AccessToken을 통해 인가를 받게 됩니다.<br>
<br><br>


### 4. 회원정보수정
 <img src="https://github.com/user-attachments/assets/3dfe06b3-91f1-4b66-8387-4e8e83d6ae88" width="40%" height="20%">
  <br><br><br>
회원정보수정은 회원가입 때, 사용했던 회원가입정보를 수정할 수 있다.<br>
동일한 닉네임, 이메일 입력 시, 에러메시지를 입력란 하단에 출력 됩니다.<br>
입력란에 공백데이터 입력시, 에러메세지를 입력란 하단에 출력 됩니다.<br>
수정 성공 시, DB내용이 수정이되며, 회원가입수정완료 토스트메세지가 출력 됩니다.<br>
<br><br>


### 5. 회원탈퇴
 <img src="https://github.com/user-attachments/assets/4ab73491-8098-46b3-b5e2-67832714ecd0" width="40%" height="20%">
  <br><br><br>
회원탈퇴 버튼 클릭 시, 인가를 통하여 인가 성공시, 선택 다이얼로그 상자가 출력 됩니다.<br>
"예" 버튼 클릭 시, 회원탈퇴가 되며, DB에 있는 내용들이 삭제되고, 인트로 화면으로 이동 합니다.<br>
<br><br>

### 6. 로그아웃
 <img src="https://github.com/user-attachments/assets/35bf9dd0-319b-49ab-9859-d893fe92e2db" width="40%" height="20%">
  <br><br><br>
로그아웃 버튼 클릭 시, 인가를 통하여 인가 성공시, 로그아웃이 되고, 인트로 화면으로 이동 됩니다.<br>
<br><br>



### 7. 프래그먼트 화면이동
 <img src="https://github.com/user-attachments/assets/4e791152-b9ed-4c37-877a-3e3b4dc23588" width="40%" height="20%">
  <br><br><br>
메인화면에서 이동하고 싶은 프래그먼트 버튼을 클릭하면 해당화면으로 이동이 가능 합니다.<br>
게시글화면,도서정보화면,채팅화면,지도화면으로 이동이 가능 합니다.
<br><br>

### 8. 게시글 작성,읽기,수정,삭제
게시글 작성은 제목,내용,게시글내용,이미지업로드란으로 구성이 되어 있습니다.<br>
이미지 업로드 같은경우, 최대 1장까지만 업로드가 가능 합니다.<br>
이미지 저장 스토리지 서버는 FireBase Storage를 사용 했습니다.<br>
사용자가 브라우저에서 업로드할 이미지 파일을 선택 후 "확인" 버튼을 클릭하면,<br>
선택한 파일 객체를 FireBaseStorage에 파일을 저장하고<br>
스토리지에서는 파일을 저장한 결과로 사진주소를 리턴 해줍니다.<br>
반환받은 이미지 주소를 브라우저에게 전달 해주는 프로세스로 구현 했습니다.

게시글 읽기는 게시글 리스트화면에서 게시글을 클릭했을때, 클릭한 게시글의 ID를 db에서 조회하여,<br>
id에 해당하는 데이터를 게시글상세화면에서 출력된다.<br>

게시글 수정은 상세게시글의 id를 db에서 조회하여 게시글 수정페이지란에 출력해준다.<br>
게시글 수정은 공란 이거나, 조건에 맞지않는 경우 입력란 하단에 에러메세지를 출력 해준다.<br>
수정완료 시, 작성한 게시글의 db내용을 수정한다.<br>

게시글 삭제는 해당 게시글의 id에 해당하는 게시글의 내용을 db에서 삭제후, 클라이언트에 바로 반영한다.
<br><br>

### 9. 댓글 작성,읽기,수정,삭제
댓글작성은 내용란으로 구성이 되어 있습니다.<br>
댓글작성은 공란일 시, 입력창 하단에 에러메세지를 출력 합니다.<br>
댓글작성 성공 시, 해당게시글의 db하위에 저장된다.<br>

댓글 읽기는 게시글 리스트화면에서 게시글을 클릭했을때, 클릭한 게시글의 ID를 db에서 조회하여,<br>
id에 해당하는 댓글리스트를 게시글상세화면에서 출력된다.<br>

댓글 수정은 해당댓글의 id를 db에서 조회하여 게시글 수정페이지란에 출력해준다.<br>
댓글 수정은 공란 이거나, 조건에 맞지않는 경우 입력란 하단에 에러메세지를 출력 해준다.<br>
수정완료 시, 작성한 댓글의 db내용을 수정한다.<br>

댓글 삭제는 댓글의 id에 해당하는 댓글의 내용을 db에서 삭제후, 클라이언트에 바로 반영한다.
<br><br>



### 10. 도서검색
 <img src="https://github.com/user-attachments/assets/6903029d-aecf-4762-9fca-18d5533a6820" width="40%" height="20%">
  <br><br><br>
도서검색 기능은 인터파크 API를 활용하여 개발을 진행 했습니다.<br>
검색하기 전, 도서리스트의 화면에서는 베스트셀러 API를 활용하여, 베스트셀러 도서들의 목록을 출력 합니다.
도서검색은 책검색API를 활용하여 검색한 키워드에 대한 도서의 리스트를 출력 합니다.
<br><br>



### 11. 지도검색
 <img src="https://github.com/user-attachments/assets/ca47d1da-9d79-4bfe-92b0-df1a4f841dca" width="40%" height="20%">
  <br><br><br>
지도검색 API는 Tmap API와 Google Map API를 활용하여 개발을 진행 했습니다.<br>
원하는 위치의 정보는 Tmap API를 활용하여 데이터를 가져오고, Google Map API를 활용하여<br>
가져온 데이터를 화면에 출력해주는 기능을 구현 했습니다.
<br><br>










