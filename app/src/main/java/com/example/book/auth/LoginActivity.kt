package com.example.book.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.book.MainActivity
import com.example.book.R
import com.example.book.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth  //FireBaseAuth 클래스의 메소드에 접근하기위해 FireBaseAuth 객체 타입으로 지정
    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        binding.loginBtn.setOnClickListener() {

            var loginJoin = true

            val loginEmail = binding.loginEmail.text.toString()

            val loginPassword = binding.loginPassword.text.toString()
            
            auth = FirebaseAuth.getInstance() //FireBaseAuth 클래스의 인스턴스 값을 반환

            if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
                Toast.makeText(this, "이메일과 비밀번호란은 공란일 수 없습니다.", Toast.LENGTH_LONG).show()
                loginJoin = false;
            }

//            if (loginPassword.isEmpty()) {
//                Toast.makeText(this, "패스워드 입력창이 비어 있습니다.", Toast.LENGTH_LONG).show()
//                loginJoin = false;
//            }







            if (loginJoin) {
                auth.signInWithEmailAndPassword(loginEmail, loginPassword) //로그인 인증 함수(공식문서제공)
                    .addOnCompleteListener(this) { task -> //하나인 인터페이스를 구현할때는 SAM 기법사용
                        if (task.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                                intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)



                            Toast.makeText(this, "로그인에 성공 하였습니다.", Toast.LENGTH_LONG).show()

                        } else {
                            Toast.makeText(this, "이메일 또는 비밀번호를 잘못 입력 하셨습니다.", Toast.LENGTH_LONG).show()
                        }
                    }


            }

        }
    }
}