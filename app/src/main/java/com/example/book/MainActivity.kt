package com.example.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.book.auth.IntroActivity
import com.example.book.auth.LoginActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.reflect.Array.get

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth  //Firebase 인증 인스턴스를 선언(공식문서기재)


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth //항상 초기화

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}