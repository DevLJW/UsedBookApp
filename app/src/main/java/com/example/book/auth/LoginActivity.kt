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


    private lateinit var auth: FirebaseAuth
    val user = Firebase.auth.currentUser

    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_login)


        val database = Firebase.database
        val CustomerData = database.getReference()//데이터 베이스 내 최상위 레퍼런스 값


        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        binding.loginBtn.setOnClickListener() {

            var loginJoin = true

            val loginEmail = binding.loginEmail.text.toString()
            val loginPassword = binding.loginPassword.text.toString()
            auth = Firebase.auth

            if (loginEmail.isEmpty()) {
                Toast.makeText(this, "이메일 입력창이 비어있습니다.", Toast.LENGTH_LONG).show()
                loginJoin = false;
            }

            if (loginPassword.isEmpty()) {
                Toast.makeText(this, "패스워드 입력창이 비어 있습니다.", Toast.LENGTH_LONG).show()
                loginJoin = false;
            }







            if (loginJoin) {
                auth.signInWithEmailAndPassword(loginEmail, loginPassword) //로그인 인증 함수(공식문서제공)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)



                            Toast.makeText(this, "로그인성공", Toast.LENGTH_LONG).show()

                        } else {
                            Toast.makeText(this, "로그인실패", Toast.LENGTH_LONG).show()
                        }
                    }


            }

        }
    }
}