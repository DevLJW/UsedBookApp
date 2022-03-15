package com.example.book.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.book.R
import com.example.book.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    //데이터바인딩 : 데이터와 뷰를 연결하는 작업을 레이아웃에서 처리하는 기술
    //findViewById()를 매번사용하지 않기 위해 사용

    private lateinit var binding : ActivityIntroBinding   //ActivtyIntroBinding의 인스턴스를 선언(공식문서기재) lateinit : 초기화 나중에

    override fun onCreate(savedInstanceState: Bundle?) { //화면이 회전되거나 크기가 변할때 화면이 다시그려질떄, 데이터 보존용
        super.onCreate(savedInstanceState)
   //     setContentView(R.layout.activity_intro)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_intro) //현재 페이지를 actvity_intro.xml파일과 연결해준다.




        binding.loginbtn.setOnClickListener{ //로그인 버튼 클릭시

            val intent = Intent(this,LoginActivity::class.java)  //현재페이지(this)에서 Login페이지로 이동
            startActivity(intent)     //startActvity 함수 실행(매개변수로  전달 받은 값)


        }

        binding.joinbtn.setOnClickListener{ //회원가입 버튼 클릭시

            val intent = Intent(this,JoinActivity::class.java)  //현재페이지(this)에서 화면이동, JoinActivity페이지로 이동
            startActivity(intent)   //startActvity 함수 실행(매개변수로  전달 받은 값)


        }

    }
}