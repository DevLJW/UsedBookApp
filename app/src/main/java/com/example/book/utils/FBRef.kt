package com.example.book.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
            //파이어베이스 주소
class FBRef {
    companion object{

        private  val database = Firebase.database

        val boardRef = database.getReference(("board")) // (경로주소)테이블 생성
        val commentRef = database.getReference(("comment")) // (경로주소)테이블 생성
    }
}