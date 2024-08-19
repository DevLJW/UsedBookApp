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
## 중고도서 사이트
![소통 메인](https://github.com/user-attachments/assets/67f6d109-1ada-4e96-b67f-13e7ee75270f)
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





### 3. 게시글 작성
 <img src="https://github.com/user-attachments/assets/eda3f001-d725-45e7-a9cd-622d91e8c961" width="40%" height="20%">
  <br><br><br>

게시글 작성 필드는 작성자,비밀번호,제목,내용,이미지 업로드란으로 구성이 되어 있습니다.<br>
이미지 업로드 같은경우, 최대 3장까지만 업로드가 가능 합니다.<br>
이미지 저장 스토리지 서버는 구글 클라우드를 사용 했습니다.<br>
사용자가 브라우저에서 업로드할 이미지 파일을 선택 후 "확인" 버튼을 클릭하면,<br>
선택한 파일 객체를 백엔드 서버에 API를 요청하여 백엔드 서버에서 구글스토리지에 파일을 저장하고<br>
스토리지에서는 파일을 저장한 결과로 사진주소를 백엔드 서버에 리턴 해줍니다.<br>
백엔드 서버에서 다시, 반환받은 이미지 주소를 브라우저에게 전달 해주는 프로세스로 구현 했습니다.
<br><br>

### 4. 게시글 상세보기
 <img src="https://github.com/user-attachments/assets/1f05ce1e-5e61-4784-988b-53d489bcf132" width="100%" height="60%">
  <br><br><br>


게시글 상세보기는 작성자,작성날짜,내용,제목,목록버튼,수정버튼,삭제버튼으로 구성되어 있습니다.<br>
게시글 리스트 화면에서 해당 게시글을 클릭하면 클릭한 게시글에 대한 상세내용을 확인 할 수 있습니다.<br>
목록버튼이나 수정버튼 같은경우, 게시글 리스트화면에서 게시글을 클릭했을때, 클릭한 게시글의 ID를 Recoil에 저장하고,<br>
백엔드 API로 Recoil에 저장되어 있는 게시글ID를 파라미터로 넘겨, 해당 게시글의 작성자의 이름과 현재 로그인한 사용자의 이름이 같다면,<br>
수정하기 버튼과 삭제하기 버튼을 노출 합니다.
<br><br>

### 5. 게시글 수정하기
 <img src="https://github.com/user-attachments/assets/24750247-1a84-49e1-83c1-e1b9073be04f" width="100%" height="60%">
  <br><br><br>

게시글 수정하기는 게시글 상세화면에서 클릭한 게시글의 ID를 백엔드 API에 파라미터로 넘겨<br> 해당 게시글의 정보를 가져와 화면에 출력 합니다.<br>
작성자란은 추후, 비활성화 할 예정이며, 수정없이 수정하기 버튼을 클릭 시, 에러 메세지를 발생 합니다.<br>
수정하기 버튼을 클릭하면 입력한 데이터들이 백엔드 API를 호출하여 파라미터로 전달되고, DB에 업데이트되는 방식으로 구현 했습니다.

<br><br>

### 6. 게시글 삭제하기
게시글 삭제하기는 게시글 상세화면에서 클릭한 게시글의 ID를 백엔드 API에 파라미터로 넘겨 해당 게시글을 삭제 합니다.<br>
삭제 시, 리렌더가 적용되어 게시글 리스트 페이지 화면에 바로 업데이트 됩니다.
<br><br>

### 7. 페이징네이션
 <img src="https://github.com/user-attachments/assets/ce895209-a1fa-485d-8033-40f67e585156" width="100%" height="100%">
  <br><br><br>
페이징네이션은 페이지당 10개의 게시글로 구성이 되어 있습니다.<br>
1~10번 게시글은 1페이지, 11~20번의 게시글은 2번페이지로 구성이 되어 있습니다.<br>
3번 페이지 클릭시, 백엔드 Refetch API에 30 + 1로 시작값이 설정이되고, 40번까지의 게시글 값을 가져오게 됩니다.
<br><br>


### 8. 댓글 작성 ~ 댓글 삭제
 <img src="https://github.com/user-attachments/assets/cf13e26e-8757-4626-9af6-4e3ca54b38f4" width="100%" height="60%">


  <br><br><br>
댓글작성과 수정은 최대 100자까지 입력이 가능하며, 입력하기 버튼시, 입력데이터가 백엔드API 파라미터로 전달되며,<br>
백엔드에서 TypeORM을 통해 DB에 저장시켜 줍니다. 수정 같은경우, 기존작성했던내용과 동일해선 안됩니다.<br>
댓글 삭제하기는 게시글 상세화면에서 삭제아이콘을 클릭한 게시글의 댓글 ID를 백엔드 API에 파라미터로 넘겨 해당 댓글을 삭제 합니다.<br>
삭제 시, 리렌더가 적용되어 게시글 상세 페이지 화면에 바로 업데이트 됩니다.

<br><br>






