package com.example.book.auth

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.AutoText
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.example.book.MainActivity
import com.example.book.R
import com.example.book.databinding.ActivityJoinBinding
import com.example.book.utils.FBAuth
import com.example.book.utils.UserJoinData

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern



public class JoinActivity : AppCompatActivity() {
     var emailPatttern = Patterns.EMAIL_ADDRESS  //안드로이드 라이브러리에서 이메일형식인지 체크해주는 기능
    private lateinit var auth: FirebaseAuth     //Firebase 인증 인스턴스를 선언(공식문서기재)
    private lateinit var binding: ActivityJoinBinding  //데이터바인딩 인스턴스 선언(공식문서기재)





    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)  //데이터 바인딩으로 인한 함수교체


        binding.joinBtn.setOnClickListener() {   // 회원가입 클릭시, 이벤트 함수

            // XML 데이터 전달
            val Email = binding.Email.text.toString()
            val password1 = binding.password1.text.toString()
            val password2 = binding.password2.text.toString()
            val phonenumber = binding.phonenumber.text.toString()
            val address = binding.address.text.toString()
            var GoJoin = true //회원가입 성공여부








            //값이 비어있는지 확인
            if (Email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }


            //이메일 형식인지 체크해주는 기능
            else if (!emailPatttern.matcher(Email.toString()).matches()) { //이메일 형식이 아닌경우
                Toast.makeText(this, "이메일 형식으로 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }



            //패스워드란 비어있는지 확인
           else if (password1.isEmpty()) {
                Toast.makeText(this, "패스워드를 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }


            //패스워드 8~20글자중 특수문자,영문자,한글이 한번씩 들어가야함(정규표현식)(이해못함)
            else if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password1)) {
                Toast.makeText(this, "비밀번호 형식을 지켜주세요.", Toast.LENGTH_SHORT).show()
                GoJoin = false
            }



            //패스워드란 비어있는지 확인
           else  if (password2.isEmpty()) {
                Toast.makeText(this, "패스워드확인을 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }




            //패스워드 1과 2가 같은지 확인
            else if (!password1.equals(password2)) {
                Toast.makeText(this, "비밀번호를 동일하게 입력 해주세요.", Toast.LENGTH_LONG)
                    .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }

            // 패스워드가 8자리 이상인지
            else if (password1.length < 8) {
                Toast.makeText(this, "비밀번호를 8자리 이상으로 입력해주세요.", Toast.LENGTH_LONG).show()
                GoJoin = false //회원가입 조건 미달시, false 값 대입
            }



           //휴대폰번호란 비어있는지 확인
            else if (phonenumber.isEmpty()) {
                Toast.makeText(this, "휴대폰번호를 입력 해주세요.", Toast.LENGTH_LONG)
                    .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }

            //휴대폰번호란 비어있는지 확인
            else if (address.isEmpty()) {
                Toast.makeText(this, "주소를 입력 해주세요.", Toast.LENGTH_LONG)
                    .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                GoJoin = false //회원가입 조건 미달시, false 값 대입

            }




            //파이어베이스 데이터베이스에 회원인증을 해주는 함수
            //Auth내 함수기능에서 중복아이디 검사를 기본적으로 해줌
            if (GoJoin) {
                auth.createUserWithEmailAndPassword(
                    Email,
                    password1
                ) //파이어베이스에서 제공하는 이메일 아이디 제공형식 로그인 함수 인자를 2개밖에 제공하지않음
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {   //회원인증 성공시,

                            val JoinUserData = UserJoinData()
                            JoinUserData.UserJoinDataMake(
                                binding.Email.text.toString(),
                                binding.password1.text.toString(),
                                binding.password2.text.toString(),
                                binding.phonenumber.text.toString(),
                                binding.address.text.toString()
                            )

                                 var uid = auth.currentUser?.uid.toString()


                            val database = Firebase.database //디비 접속
                            val CustomerData = database.getReference()//데이터 베이스 내 최상위 레퍼런스 값

                            // 유저 키값으로 생성하는 코드 첫번째가 키값이 아니면 오류남.
                            //최상위 레퍼런스 값 -> UserInfo -> uid값 -> 지정한값들 생성
                            CustomerData.child("UserInfo").child(uid.toString()).child("UserUID")
                                .setValue(uid)
                            CustomerData.child("UserInfo").child(uid.toString()).child("UserEmail")
                                .setValue(JoinUserData.UserEmail)
                            CustomerData.child("UserInfo").child(uid.toString())
                                .child("UserPassword").setValue(JoinUserData.UserPassWordOne)
                            CustomerData.child("UserInfo").child(uid.toString())
                                .child("UserPhoneNumber").setValue(JoinUserData.UserPhoneNumber)
                            CustomerData.child("UserInfo").child(uid.toString())
                                .child("UserAddress").setValue(JoinUserData.UserAddress)


                            val intent =
                                Intent(this, LoginActivity::class.java)  //Intent 화면 전환용으로 사용
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //성공시, 화면전환
                            startActivity(intent)


                            Toast.makeText(this, "회원가입성공", Toast.LENGTH_LONG).show()


                        } else {  //로그인 실패 시,
                            Toast.makeText(this, "이미 등록되어있는 회원 입니다.", Toast.LENGTH_LONG).show()

                        }
                    }


                // }


            }






        }
        auth = Firebase.auth //OnCreate() 메소드에서 FirebaeAuth 인스턴스를 초기화.(공식문서기재)

    }
}