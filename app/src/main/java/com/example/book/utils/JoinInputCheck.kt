/*package com.example.book.utils

import android.util.Patterns
import android.widget.Toast
import com.example.book.auth.JoinActivity
import java.util.regex.Pattern

class JoinInputCheck {

    var GoJoin: Boolean = true //회원가입 성공여부

    fun check(a : String, b : String, c : String, d : String, e : String) : Boolean {
        var emailPatttern = Patterns.EMAIL_ADDRESS  //안드로이드 라이브러리에서 이메일형식인지 체크해주는 기능
        var JoinActivity1 = JoinActivity()


        //값이 비어있는지 확인
        if (a.isEmpty()) {
            Toast.makeText(JoinActivity1.applicationContext, "이메일을 입력 해주세요.", Toast.LENGTH_LONG)
                .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
            GoJoin = false //회원가입 조건 미달시, false 값 대입

        }


        //이메일 형식인지 체크해주는 기능
        else if (!emailPatttern.matcher(b.toString())
                .matches()) { //이메일 형식이 아닌경우
            Toast.makeText(JoinActivity1.applicationContext, "이메일 형식으로 입력 해주세요.", Toast.LENGTH_LONG)
                .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
            GoJoin = false //회원가입 조건 미달시, false 값 대입

        }


        //패스워드란 비어있는지 확인
        else if (b.isEmpty()) {
            Toast.makeText(JoinActivity1.applicationContext, "패스워드를 입력 해주세요.", Toast.LENGTH_LONG)
                .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
            GoJoin = false //회원가입 조건 미달시, false 값 대입

        }


        //패스워드 8~20글자중 특수문자,영문자,한글이 한번씩 들어가야함(정규표현식)(이해못함)
        else if (!Pattern.matches(
                "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$",
                b
            )
        ) {
            Toast.makeText(JoinActivity1.applicationContext, "비밀번호 형식을 지켜주세요.", Toast.LENGTH_SHORT).show()
            GoJoin = false
        }


        //패스워드란 비어있는지 확인
        else if (c.isEmpty()) {
            Toast.makeText(JoinActivity1.applicationContext, "패스워드확인을 입력 해주세요.", Toast.LENGTH_LONG)
                .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
            GoJoin = false //회원가입 조건 미달시, false 값 대입

        }


        //패스워드 1과 2가 같은지 확인
        else if (b.equals(c)) {
            Toast.makeText(JoinActivity1.applicationContext, "비밀번호를 동일하게 입력 해주세요.", Toast.LENGTH_LONG)
                .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
            GoJoin = false //회원가입 조건 미달시, false 값 대입

        }

        // 패스워드가 8자리 이상인지
        else if (a.length < 8) {
            Toast.makeText(JoinActivity1.applicationContext, "비밀번호를 8자리 이상으로 입력해주세요.", Toast.LENGTH_LONG).show()
            GoJoin = false //회원가입 조건 미달시, false 값 대입
        }


        //휴대폰번호란 비어있는지 확인
        else if (d.isEmpty()) {
            Toast.makeText(JoinActivity1.applicationContext, "휴대폰번호를 입력 해주세요.", Toast.LENGTH_LONG)
                .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
            GoJoin = false //회원가입 조건 미달시, false 값 대입

        }

        //휴대폰번호란 비어있는지 확인
        else if (e.isEmpty()) {
            Toast.makeText(JoinActivity1.applicationContext, "주소를 입력 해주세요.", Toast.LENGTH_LONG)
                .show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
            GoJoin = false //회원가입 조건 미달시, false 값 대입

        }
        return GoJoin
    }
}
*/








