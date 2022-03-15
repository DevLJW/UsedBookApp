package com.example.book
import com.example.book.auth.IntroActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//화면이동 페이지

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        auth = Firebase.auth



        if(auth.currentUser?.uid == null){ //현재 로그인이 안되어 있으면, 인트로페이지로 이동
           Handler().postDelayed( {
              startActivity(Intent(this,IntroActivity::class.java)) // this 현재 페이지를 실행하고 ,IntroActvity로 이동을 하겠다.
                finish() //현재 액티비티를 끝내주고
            }, 3000) //3초후에 이동을 하겠다.


        }else{
            Handler().postDelayed( { //현재 로그인이 되어있을떄, 메인페이지로 이동한다.
                startActivity(Intent(this,MainActivity::class.java)) // this 현재 페이지를 실행하고 ,IntroActvity로 이동을 하겠다.
                finish() //현재 액티비티를 끝내주고
            }, 3000) //3초후에 이동을 하겠다.


        }



        }
    }
