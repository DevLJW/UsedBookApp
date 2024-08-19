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
## CRUD기반 커뮤니티 사이트
![소통 메인](https://github.com/user-attachments/assets/67f6d109-1ada-4e96-b67f-13e7ee75270f)
<br>


  
## 개발동기
사용자들끼리 서로 소통이 가능한 CRUD 기반 커뮤니티 사이트를 제작 했습니다.<br>
대부분의 사이트들에 있는 게시판,회원가입,로그인 기능들을 Front,Backend,DB를 학습해보고<br>
1인 개발에 적절한 범위라고 생각하여 개발을 하게 되었습니다.
<br>
<br>

## 개발기간
2024.01.10 ~ 2024.03.10
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
<img src="https://img.shields.io/badge/Next.js-000000?style=for-the-badge&logo=Next.js&logoColor=white">
<img src="https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=TypeScript&logoColor=white">
<img src="https://img.shields.io/badge/ReactHookForm-EC5990?style=for-the-badge&logo=ReactHookForm&logoColor=black">
<img src="https://img.shields.io/badge/Recoil-3578E5?style=for-the-badge&logo=Recoil&logoColor=black">
<img src="https://img.shields.io/badge/Emotion-EC5990?style=for-the-badge&logo=Emotion&logoColor=black">
</div>

### BackEnd
<div>
<img src="https://img.shields.io/badge/NestJS-E0234E?style=for-the-badge&logo=NestJS&logoColor=black">
</div>

### DataBase
<div>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=black">
<img src="https://img.shields.io/badge/TypeORM-FE0803?style=for-the-badge&logo=TypeORM&logoColor=black">
</div>

### API
<div>
<img src="https://img.shields.io/badge/GraphQL-E10098?style=for-the-badge&logo=GraphQL&logoColor=black">
</div>

<br>
<br>

## 주요기능소개
### 1. 회원가입
 <img src="https://github.com/user-attachments/assets/089e63cb-d3f5-4584-a6fd-2e3500c0484d" width="80%" height="60%">
 <br><br><br>
회원가입 필드는, 이메일,이름,닉네임,패스워드,패스워드 확인,연락처 인증번호 확인란으로 구성이 되어 있습니다.<br>
Form 같은 경우, React-Hook-Form으로 구성을 했습니다. 리렌더를 최소화 시키고, 실시간 동기화를 하기 위하여 사용 했습니다.<br>
인증 메세지 같은경우, Cool-SMS API를 사용 했습니다.<br>
회원가입 성공 시, JOIN_USER API를 호출하여 입력받은 값을 TypeOrm을 통하여 DB에 저장시킵니다.<br>
패스워드 같은 경우, crypto 라이브러리를 통하여 암호화 시킨 후 DB에 저장 합니다.





<br><br>


### 2. 로그인
 <img src="https://github.com/user-attachments/assets/890ca0dc-cd72-45b4-be05-fd2373b08c3f" width="80%" height="60%">
  <br><br><br>
로그인 필드는 이메일,패스워드 필드란으로 구성이 되어있고, React-Hook_Form으로 구성 했습니다.<br>
입력한 데이터가 DB의 데이터와 일치하면, 로그인이 성공 합니다.<br>
패스워드 같은경우 백엔드에서 복호화를 통하여 입력한 패스워드와 DB의 패스워드와 일치한지 비교 합니다.<br>
로그인 성공 시, 사용자에게 AccessToken을 부여하며 글로벌 스테이트 인 Recoil에 저장 합니다. 부여받은 AccessToken을 통해 인가를 받게 됩니다.
현재는 로그인 시, AccessToken을 부여하는 방식 이지만, 추후 RefreshToken을 추가하여 AccessToken 만료시, RefreshToken을 통하여 재발급 예정 입니다.


### 3. 게시글 작성
 <img src="https://github.com/user-attachments/assets/eda3f001-d725-45e7-a9cd-622d91e8c961" width="80%" height="60%">
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






