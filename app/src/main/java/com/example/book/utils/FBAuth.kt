
package com.example.book.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class FBAuth { //UID 가져오는 클래스
    companion object {


        private lateinit var auth: FirebaseAuth  //UID 값 반환함수

        fun getUid(): String? {

            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()


        }


        fun getTime(): String {            //현재시각 반환 함수
            val currentDateTime = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)
            return dateFormat

        }

    }
}



