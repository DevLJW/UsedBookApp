package com.example.book.utils

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.book.R
import com.example.book.databinding.ActivityJoinBinding


public class UserJoinData() {

       lateinit var UserEmail : String
       lateinit var UserPassWordOne : String
       lateinit var UserPassWordTwo : String
       lateinit var UserPhoneNumber : String
       lateinit var UserAddress : String

    //회원가입시 입력한 데이터 --> UserInfo DB로 이동
    fun UserJoinDataMake(a: String, b: String, c: String, d: String,e : String){
        this.UserEmail = a
        this.UserPassWordOne = b
        this.UserPassWordTwo = c
        this.UserPhoneNumber = d
        this.UserAddress = e

    }


}




