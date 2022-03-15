/*package com.example.book.utils

import com.example.book.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserInfoMake {
    private lateinit var auth: FirebaseAuth     //Firebase 인증 인스턴스를 선언(공식문서기재)
    private lateinit var binding: ActivityJoinBinding
    val database = Firebase.database //디비 접속
   val CustomerData1 = database.getReference()//데이터 베이스 내 최상위 레퍼런스 값
    var uid = auth.currentUser?.uid //현재 인증페이지 유저 키값 가져오기

    CustomerData.child("UserInfo").child(uid.toString()).child("UserUID").setValue(uid)
  //CustomerData.child("UserInfo").child(uid.toString()).child("UserEmail").setValue(Email)
  //CustomerData.child("UserInfo").child(uid.toString()).child("UserPassword").setValue(password1)
   // CustomerData.child("UserInfo").child(uid.toString()).child("UserPhoneNumber").setValue(phonenumber)
   // CustomerData.child("UserInfo").child(uid.toString()).child("UserAddress").setValue(address)



}
*/
