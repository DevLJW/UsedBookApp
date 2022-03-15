package com.example.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.book.auth.IntroActivity

import com.example.book.databinding.ActivityIntroBinding
import com.example.book.databinding.ActivityUserUpadateBinding
import com.example.book.utils.UserUpadeData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class UserUpadateActivity : AppCompatActivity() {

    lateinit var Email: String
    lateinit var Password: String
    lateinit var Address: String
    lateinit var PhoneNumber: String

    lateinit var oldEmail: String
    lateinit var oldPassword: String
    lateinit var oldAddress: String
    lateinit var oldPhoneNumber: String
    lateinit var alldata: String


    val user = Firebase.auth.currentUser
    private lateinit var binding: ActivityUserUpadateBinding
    var UserUpdateData = UserUpadeData()
    val user100 = Firebase.auth.currentUser?.uid //현재 로그인한 사용자
    val database = Firebase.database       //읽고쓰기위한 변수생성
    val CustomerData = database.getReference() //데이터베이스 주소값 가져오기
    private lateinit var auth: FirebaseAuth
    var emailPatttern = Patterns.EMAIL_ADDRESS


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, (R.layout.activity_user_upadate))

        // var content1 : String = UserUpdateData.UserEmail1.


        //전체데이터읽기


        //레거시데이터 읽기
        CustomerData.child("UserInfo").child(user100.toString()).child("UserEmail").get()
            .addOnSuccessListener { //Firebase 데이터 읽어오기

                binding.UserUpdateEmail.setText(it.value.toString()!!)
                this.oldEmail = it.value.toString()
            }

        CustomerData.child("UserInfo").child(user100.toString()).child("UserPassword").get()
            .addOnSuccessListener { //Firebase 데이터 읽어오기

                binding.UserUpdatePassword1.setText(it.value.toString()!!)
                this.oldPassword = it.value.toString()
            }

        CustomerData.child("UserInfo").child(user100.toString()).child("UserAddress").get()
            .addOnSuccessListener { //Firebase 데이터 읽어오기  Log.i("firebase", "Got value ${it.value}")
                binding.UserUpdateAddress.setText(it.value.toString()!!)
                this.oldAddress = it.value.toString()
            }

        CustomerData.child("UserInfo").child(user100.toString()).child("UserPhoneNumber").get()
            .addOnSuccessListener { //Firebase 데이터 읽어오기

                binding.UserUpdatePhonenumber.setText(it.value.toString()!!)
                this.oldPhoneNumber = it.value.toString()
            }




        CustomerData.child("UserInfo").get()
            .addOnSuccessListener { //Firebase 데이터 읽어오기


                binding.alldata.setText(it.value.toString())
                this.alldata = it.value.toString()

            }




        binding.UserUpdateBtn.setOnClickListener() {

            var join = true

            //값수정을 하나도안했을때

            if (binding.alldata.text.contains(binding.UserUpdateEmail.text.toString()) &&
                binding.UserUpdatePassword1.text.toString() == oldPassword.toString()
                && binding.UserUpdatePhonenumber.text.toString() == oldPhoneNumber.toString() &&
                binding.UserUpdateAddress.text.toString() == oldAddress) {
                        Toast.makeText(this, "이미등록 되어있는 아이디입니다. ", Toast.LENGTH_LONG).show()
                         join = false
        }

            //아이디는 같은데 다른값을 수정했을때
             if(binding.alldata.text.contains(binding.UserUpdateEmail.text.toString())){
                 if( binding.UserUpdatePassword1.text.toString() != oldPassword.toString()
                     || binding.UserUpdatePhonenumber.text.toString() != oldPhoneNumber.toString() ||
                        binding.UserUpdateAddress.text.toString() != oldAddress){
                            join = true

                }

            }
                 //아이디만 수정했을때
            if(!binding.alldata.text.contains(binding.UserUpdateEmail.text.toString())){
                if( binding.UserUpdatePassword1.text.toString() == oldPassword.toString()
                    && binding.UserUpdatePhonenumber.text.toString() == oldPhoneNumber.toString() &&
                    binding.UserUpdateAddress.text.toString() == oldAddress){
                    join = true

                }

            }



            //이메일 비었는지 확인
            if (binding.UserUpdateEmail.text.toString().isEmpty()) {
                Toast.makeText(this, "이메일을 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                join = false //회원가입 조건 미달시, false 값 대입

            }

            //패스워드란 비어있는지 확인
            if (binding.UserUpdatePassword1.text.toString().isEmpty()) {
                Toast.makeText(this, "패스워드를 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                join = false //회원가입 조건 미달시, false 값 대입

            }

            //휴대폰번호란 비어있는지 확인
            if (binding.UserUpdatePhonenumber.text.toString().isEmpty()) {
                Toast.makeText(this, "휴대폰번호를 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                join = false //회원가입 조건 미달시, false 값 대입

            }

            //주소란 비어있는지 확인
            if (binding.UserUpdateAddress.text.toString().isEmpty()) {
                Toast.makeText(this, "주소를 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                join = false //회원가입 조건 미달시, false 값 대입

            }



            //이메일 형식인지 체크해주는 기능
            if (!emailPatttern.matcher(binding.UserUpdateEmail.text.toString().toString()).matches()) { //이메일 형식이 아닌경우
                Toast.makeText(this, "이메일 형식으로 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력
                join = false //회원가입 조건 미달시, false 값 대입

            }



            //패스워드 8~20글자중 특수문자,영문자,한글이 한번씩 들어가야함(정규표현식)(이해못함)
            if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.UserUpdatePassword1.text.toString())) {
                Toast.makeText(this, "비밀번호 형식을 지켜주세요.", Toast.LENGTH_SHORT).show()
                join = false
            }







           if(join) {


                //멤버변수에 값 전달

                this.Email = binding.UserUpdateEmail.text.toString()
                this.Password = binding.UserUpdatePassword1.text.toString()
                this.Address = binding.UserUpdateAddress.text.toString()
                this.PhoneNumber = binding.UserUpdatePhonenumber.text.toString()



                user!!.updateEmail(this.Email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "정보가 변경되었습니다. ", Toast.LENGTH_LONG).show()

                            user!!.updatePassword(this.Password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this, "정보가 변경되었습니다.", Toast.LENGTH_LONG)
                                            .show()

                                        CustomerData.child("UserInfo").get()
                                            .addOnSuccessListener { //Firebase 데이터 읽어오기
                                                binding.alldata.setText(it.value.toString())
                                                this.alldata = it.value.toString()




                                            }
                                        val intent = Intent(this, MainActivity::class.java)
                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(intent)



                                    }
                                }
                        }
                    }

                //데이터 Update()
                CustomerData.child("UserInfo").child(user100.toString()).child("UserEmail")
                    .setValue(this.Email)
                CustomerData.child("UserInfo").child(user100.toString()).child("UserPassword")
                    .setValue(this.Password)
                CustomerData.child("UserInfo").child(user100.toString()).child("UserAddress")
                    .setValue(this.Address)
                CustomerData.child("UserInfo").child(user100.toString()).child("UserPhoneNumber")
                    .setValue(this.PhoneNumber)



            }



        }
        auth = Firebase.auth




    }
}









