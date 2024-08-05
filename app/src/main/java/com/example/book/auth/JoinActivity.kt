package com.example.book.auth

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.provider.ContactsContract
import android.text.AutoText
import android.text.TextUtils.isDigitsOnly
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil

import com.example.book.MainActivity
import com.example.book.NameRead.NameReadModel
import com.example.book.R
import com.example.book.databinding.ActivityJoinBinding
import com.example.book.utils.FBAuth
import com.example.book.utils.FBAuth.Companion.getUid
import com.example.book.utils.UserJoinData
import com.google.android.gms.tasks.Task

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern
import kotlin.math.log

//회원가입 페이지


public class JoinActivity : AppCompatActivity() {
    var emailPatttern = Patterns.EMAIL_ADDRESS  //안드로이드 라이브러리에서 이메일형식인지 체크해주는 기능

    var check = "/^[0-9]+$/";
    private lateinit var auth: FirebaseAuth     //Firebase 인증 인스턴스를 선언(공식문서기재)
    private lateinit var binding: ActivityJoinBinding  //데이터바인딩 인스턴스 선언(공식문서기재)
    val database = Firebase.database
    val DataBaseReference = database.getReference() //데이터베이스 주소값 가져오기
    val NowLoginUser = Firebase.auth.currentUser?.uid //현재 로그인한 사용자

    var NickNameDoubleCheck = true
    var EmailDoubleCheck = true
    var CellPhoneNumberDoubleCheck = true



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)  //데이터 바인딩으로 인한 함수교체
        val user100 = Firebase.auth.currentUser?.uid //현재 로그인한 사용자
        val database = Firebase.database

        val str:String = ""
         lateinit var UserData : String










        binding.joinBtn.setOnClickListener() {   // 회원가입 클릭시, 이벤트 함수



            var GoJoin = true //회원가입 성공여부

            // XML 데이터 전달
            val Email = binding.Email.text.toString()
            val NickName = binding.NickName.text.toString()
            val password1 = binding.password1.text.toString()
            val password2 = binding.password2.text.toString()

//          val phonenumber = binding.phonenumber.text.toString()
////        val address = binding.address.text.toString()
            var a = 0;


            val CustomerData = database.getReference()

            // My top posts by number of stars


//            DataBaseReference.child("UserInfo").get()
//                .addOnSuccessListener { //Firebase 데이터 읽어오기
//                    Log.d("현재 로그인한 사용자의 이름",it.value.toString())
////                binding.UserUpdateNickName.setText(it.value.toString()!!)
////                this.oldNickName = it.value.toString()
//                }

            //중복 닉네임
            DataBaseReference.child("UserInfo").orderByChild("UserNickName").equalTo(NickName)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (!dataSnapshot.exists()) {

                            NickNameDoubleCheck = true;
                        } else {
                            binding.NickNameIssue.visibility = View.VISIBLE
                            binding.NickNameIssue.setText("중복된 닉네임 입니다.").toString()
                            NickNameDoubleCheck = false;

                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(applicationContext,
                            databaseError.message,
                            Toast.LENGTH_SHORT).show()
                    }
                })

            //중복 이메일
            DataBaseReference.child("UserInfo").orderByChild("UserEmail").equalTo(Email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (!dataSnapshot.exists()) {

                            EmailDoubleCheck = true

                        } else {

                            binding.EmailIssue.visibility = View.VISIBLE
                            binding.EmailIssue.setText("중복된 이메일 입니다.").toString()
                            EmailDoubleCheck = false
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(applicationContext,
                            databaseError.message,
                            Toast.LENGTH_SHORT).show()
                    }
                })










            //이메일이 빈값일때,
            if (Email.isEmpty()) {


                binding.EmailIssue.visibility = View.VISIBLE
                binding.EmailIssue.setText("이메일은 공백일 수 없습니다.").toString()
                GoJoin = false //회원가입 조건 미달시, false 값 대입


            }


            //이메일이 30글자를 초과 했을 때,
            if (Email.length  > 30) {
                binding.EmailIssue.visibility = View.VISIBLE
                binding.EmailIssue.setText("이메일은 30자 이하로 입력 해주세요.").toString()

                GoJoin = false //회원가입 조건 미달시, false 값 대입
            }

            //15글자가 넘고 이메일 형식이 아닐떄,
            if (Email.length  >= 15 && !emailPatttern.matcher(Email.toString()).matches()) {
                binding.EmailIssue.visibility = View.VISIBLE
                binding.EmailIssue.setText("이메일 형식으로 입력 해주세요.").toString()

                GoJoin = false //회원가입 조건 미달시, false 값 대입
            }



            //15글자 이하 일떄 ,
            if (Email.length  <= 15 && !Email.isEmpty()) {
                binding.EmailIssue.visibility = View.VISIBLE
                binding.EmailIssue.setText("이메일은 15자 이상 입력 해주세요.").toString()

                GoJoin = false //회원가입 조건 미달시, false 값 대입
            }

            //15글자가 넘고 30자 이하 면서 이메일 형식 이고, 중복 값이 없을때 [정상값 ]
             if (Email.length  >= 15 && emailPatttern.matcher(Email.toString()).matches() && Email.length <= 30 && NickNameDoubleCheck === true) {
                binding.EmailIssue.visibility = View.GONE
                GoJoin = false
                 a = a+1

            }





              // 닉네임란이 비어있는지 확인
             if (NickName.isEmpty()) {

                binding.NickNameIssue.visibility = View.VISIBLE
                binding.NickNameIssue.setText("닉네임은 공백일 수 없습니다.").toString()
                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }

            //  닉네임이 8글자를 초과 했을 때,
           if (NickName.length  > 8) {
                binding.NickNameIssue.visibility = View.VISIBLE
                binding.NickNameIssue.setText("닉네임은 8글자 이하로 입력 해주세요.").toString()

                GoJoin = false //회원가입 조건 미달시, false 값 대입
            }

            //  닉네임이 2글자가 넘고 특수문자가 포함되었을때
//            공백, 한글, 알파벳, 숫자 허용
//            nickname을 인수로 불러와서 nickname이 공백, 한글, 알파벳, 숫자만 포함하고 있는 경우 true를 반환. 즉 특수문자를 포함한 경우 false반환.

            if (NickName.length  >= 2 && !Pattern.matches("^[a-zA-Z0-9ㄱ-ㅣ가-힣]*$",NickName)) {
                binding.NickNameIssue.visibility = View.VISIBLE
                binding.NickNameIssue.setText("닉네임은 영문과 숫자 한글 조합만 입력 가능합니다.").toString()

                GoJoin = false //회원가입 조건 미달시, false 값 대입
            }


            //2글자 이하 일떄 ,
            if (NickName.length  <= 2 && !NickName.isEmpty()) {
                binding.NickNameIssue.visibility = View.VISIBLE
                binding.NickNameIssue.setText("닉네임은 2글자 이상이어야 합니다.").toString()

                GoJoin = false //회원가입 ㅣ조건 미달시, false 값 대입
            }


            //  닉네임이 2글자가 넘고 8글자보다 작으며 특수문자가 포함이 안되어있을때(영어,한글,숫자만씀) +닉중복 X [정상값]
            if (NickName.length  >= 2  && Pattern.matches("^[a-zA-Z0-9ㄱ-ㅣ가-힣]*$",NickName) && NickName.length  <= 8  && NickNameDoubleCheck === true) {
                binding.NickNameIssue.visibility = View.GONE
//                binding.NickNameIssue.setText("닉네임에는 특수문자가 포함되면 안됩니다.").toString()

                    GoJoin = false //회원가입 조건 미달시, false 값 대입
                a = a+1
            }






            //패스워드란 비어있는지 확인
           if (password1.isEmpty()) {


                 binding.PassWord1Issue.visibility = View.VISIBLE
               binding.PassWord1Issue.setText("비밀번호는 공백일 수 없습니다..").toString()
                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }

            //패스워드가 20글자를 초과 했을 때,
            if (password1.length  > 20) {
                binding.PassWord1Issue.visibility = View.VISIBLE
                binding.PassWord1Issue.setText("비밀번호는 20자 이하로 입력 해주세요.").toString()

                GoJoin = false //회원가입 조건 미달시, false 값 대입
            }

            //패스워드가 8글자 미만 일때,
            if (password1.length  < 8 && !password1.isEmpty() ) {
                binding.PassWord1Issue.visibility = View.VISIBLE
                binding.PassWord1Issue.setText("비밀번호는 8자 이상으로 입력 해주세요.").toString()

                GoJoin = false //회원가입 조건 미달시, false 값 대입
            }

            //패스워드가 8글자가 넘고 패스워드 형식이 아닐떄,
            if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password1) && password1.length >= 8 && password1.length <= 20) {
                binding.PassWord1Issue.visibility = View.VISIBLE
                binding.PassWord1Issue.setText("비밀번호는 특수문자,영문,숫자로 조합해서 입력 해주세요").toString()


                GoJoin = false
            }


            //패스워드가 8글자가 넘고 20자 이하면서 패스워드 형식일 떄(8~20글자중 특수문자,영문자,숫자가 한번씩 들어가야함) + 중복값이 아닐때, [정상값]
            if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password1) && password1.length >= 8 && password1.length <= 20 && CellPhoneNumberDoubleCheck === true) {

                binding.PassWord1Issue.visibility = View.GONE
                GoJoin = false
                a = a+1
            }




            //패스워드 1과 2과 공백과 같다면
            if (password1.isEmpty() && password2.isEmpty() && str.isEmpty()) {

                binding.PassWord2Issue.visibility = View.VISIBLE
                binding.PassWord2Issue.setText("비밀번호 확인은 공백일 수 없습니다.").toString()

                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }



            //패스워드 1과 2과 값이 동일하면 [정상값]

             if (password1.length > 0 && password2 == password1) {

                 binding.PassWord2Issue.visibility = View.GONE


                 GoJoin = false //회원가입 조건 미달시, false 값 대입
                 a = a+1

            }



            //패스워드 1과 2과  같지않다면
            if (password1 != password2) {

                binding.PassWord2Issue.visibility = View.VISIBLE
                binding.PassWord2Issue.setText("비밀번호와 동일하게 입력 해주세요.").toString()
                GoJoin = false //회원가입 조건 미달시, false 값 대입


            }















            //파이어베이스 데이터베이스에 회원인증을 해주는 함수
            //Auth내 함수기능에서 중복아이디 검사를 기본적으로 해줌
            if (a == 4) {
                //FireBaseAuth 클래스안의 createuser..메소드 아래 addOncom..메소드 호출
                auth.createUserWithEmailAndPassword(Email, password1) //파이어베이스에서 제공하는 이메일 아이디 제공형식 로그인 함수 인자를 2개밖에 제공하지않음
                    .addOnCompleteListener(this){ task -> //이벤트 핸들러 createUser(회원 생성 함수가 완료 됬을때 실행됨)
                        if (task.isSuccessful) {   //AuthResault 타입의 task의 값이 suceess인 경우,

                            val JoinUserData = UserJoinData() //JOinUserData 객체 생성
                            var getuid = getUid() //현재 사용자의 UID 값 추출
                            JoinUserData.UserJoinDataMake(binding.Email.text.toString(), binding.password1.text.toString(), binding.password2.text.toString(),getuid.toString(),binding.NickName.text.toString())




                            val database = Firebase.database //FireBase 데이터베이스 연결값 추출
                            val CustomerData = database.getReference()//FireBase 최상위 레퍼런스 값 추출 함수

                            // 유저 키값으로 생성하는 코드 첫번째가 키값이 아니면 오류남.
                            //최상위 레퍼런스 값 -> UserInfo -> uid값 -> 지정한값들 생성
                            CustomerData.child("UserInfo").child(JoinUserData.UserUid.toString()).child("UserUID").setValue(JoinUserData.UserUid)
                            CustomerData.child("UserInfo").child(JoinUserData.UserUid.toString()).child("UserEmail").setValue(JoinUserData.UserEmail)
                            CustomerData.child("UserInfo").child(JoinUserData.UserUid.toString()).child("UserNickName").setValue(JoinUserData.UserNickName)
                            CustomerData.child("UserInfo").child(JoinUserData.UserUid.toString()).child("UserPassword").setValue(JoinUserData.UserPassWordOne)

                            //최상위 레퍼런스 값 하위 이름을 UserInfo 테이블 이라고 지정하고, 하위에 UID, 하위에 UID,EMAIL,PASSWORD 등등 값 저장

                            val intent = Intent(this, LoginActivity::class.java)  //현재 페이지에서 로그인 페이지로 이동하는 값 저장
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //기존 스택에 쌓여있는 Activity 모두제거
                            startActivity(intent)


                            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_LONG).show()


                        } else {  //로그인 실패 시,
                            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_LONG).show()

                        }
                    }


                // }


            }






        }

        auth = Firebase.auth //OnCreate() 메소드에서 FirebaeAuth 인스턴스를 초기화. 기존값 초기화

    }
}