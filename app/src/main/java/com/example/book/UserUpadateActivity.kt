package com.example.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.book.auth.IntroActivity

import com.example.book.databinding.ActivityIntroBinding
import com.example.book.databinding.ActivityUserUpadateBinding
import com.example.book.utils.UserUpadeData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class UserUpadateActivity : AppCompatActivity() {


    lateinit var oldEmail: String
    lateinit var oldNickName: String
    lateinit var oldPassword: String

    lateinit var alldata: String
    lateinit var Email:String
    lateinit var NickName:String
    lateinit var Password:String



    val user = Firebase.auth.currentUser
    private lateinit var binding: ActivityUserUpadateBinding
    var UserUpdateData = UserUpadeData()
    val user100 = Firebase.auth.currentUser?.uid //현재 로그인한 사용자
    val database = Firebase.database       //읽고쓰기위한 변수생성
    val CustomerData = database.getReference() //데이터베이스 주소값 가져오기
    private lateinit var auth: FirebaseAuth
    var emailPatttern = Patterns.EMAIL_ADDRESS
    var NickNameDoubleCheck = false;
    var EmailDoubleCheck = false;
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, (R.layout.activity_user_upadate))

        // var content1 : String = UserUpdateData.UserEmail1.


        //전체데이터읽기


        //기존 이메일 데이터 가져오기
        CustomerData.child("UserInfo").child(user100.toString()).child("UserEmail").get()
            .addOnSuccessListener { //Firebase 데이터 읽어오기

                binding.UserUpdateEmail.setText(it.value.toString()!!)
                this.oldEmail = it.value.toString()
            }

        //기존 닉네임 데이터 가져오기
        CustomerData.child("UserInfo").child(user100.toString()).child("UserNickName").get()
            .addOnSuccessListener { //Firebase 데이터 읽어오기

                binding.UserUpdateNickName.setText(it.value.toString()!!)
                this.oldNickName = it.value.toString()
            }


        //기존 패스워드 데이터 가져오기
        CustomerData.child("UserInfo").child(user100.toString()).child("UserPassword").get()
            .addOnSuccessListener { //Firebase 데이터 읽어오기

                binding.UserUpdatePassword1.setText(it.value.toString()!!)
                this.oldPassword = it.value.toString()
            }






        CustomerData.child("UserInfo").get()
            .addOnSuccessListener { //Firebase 데이터 읽어오기

                binding.alldata.setText(it.value.toString())
                this.alldata = it.value.toString()

            }




        binding.UserUpdateBtn.setOnClickListener() {

            var a = 0;




            var getemailvalue = binding.UserUpdateEmail.text.toString()
            var getnicknamevalue = binding.UserUpdateNickName.text.toString()
            var getpasswordvalue = binding.UserUpdatePassword1.text.toString()

            // 기존값에서 수정사항이 없을떄



            if(getemailvalue == this.oldEmail && getnicknamevalue == this.oldNickName && getpasswordvalue == this.oldPassword){
                Toast.makeText(this, "수정사항이 없습니다.", Toast.LENGTH_LONG).show()


            }

            //중복 이메일
            CustomerData.child("UserInfo").orderByChild("UserEmail").equalTo(getemailvalue)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            EmailDoubleCheck = true;
                                a = a + 1
                            //이메일만 수정했을때
                            if(!oldEmail.contains(binding.UserUpdateEmail.text.toString())){
                                if( binding.UserUpdatePassword1.text.toString() == oldPassword.toString()
                                    && binding.UserUpdateNickName.text.toString() == oldNickName.toString()){

                                    binding.UserUpdateEmailIssue.visibility = View.GONE

                                    binding.UserUpdateNickNameIssue.visibility = View.GONE
                                    NickNameDoubleCheck = true;
                                    a = a + 1

                                }

                            }

                            //패스워드만 수정했을때
                            if(!oldPassword.contains(binding.UserUpdatePassword1.text.toString())){
                                if( binding.UserUpdateNickName.text.toString() == oldNickName.toString()
                                    && binding.UserUpdateEmail.text.toString() == oldEmail.toString()){

                                    binding.UserUpdatePassword1Issue.visibility = View.GONE
                                    NickNameDoubleCheck = true
                                    EmailDoubleCheck = true
                                    a = a + 1

                                }

                            }

                            //닉네임만 수정했을때
                            if(!oldNickName.contains(binding.UserUpdateNickName.text.toString())){
                                if( binding.UserUpdateEmail.text.toString() == oldEmail.toString()
                                    && binding.UserUpdatePassword1.text.toString() == oldPassword.toString()){

                                    binding.UserUpdateEmailIssue.visibility = View.GONE


                                    EmailDoubleCheck = true
                                    a = a + 1

                                }

                            }


                        } else {


                                if(getemailvalue != oldEmail){
                                    binding.UserUpdateEmailIssue.visibility = View.VISIBLE
                                    binding.UserUpdateEmailIssue.setText("이미 존재하는 이메일 입니다.").toString()

                                    EmailDoubleCheck = false;
                                }

                            if(getnicknamevalue != oldNickName){
                                binding.UserUpdateNickNameIssue.visibility = View.VISIBLE
                                binding.UserUpdateNickNameIssue.setText("이미 존재하는 닉네임 입니다.").toString()

                                NickNameDoubleCheck = false;
                            }







                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(applicationContext,
                            databaseError.message,
                            Toast.LENGTH_SHORT).show()
                    }
                })

            //중복 닉네임
            CustomerData.child("UserInfo").orderByChild("UserNickName").equalTo(getnicknamevalue)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (!dataSnapshot.exists()) {

                            NickNameDoubleCheck = true;
                            a= a+1


                            //이메일만 수정했을때
                            if(!oldEmail.contains(binding.UserUpdateEmail.text.toString())){
                                if( binding.UserUpdatePassword1.text.toString() == oldPassword.toString()
                                    && binding.UserUpdateNickName.text.toString() == oldNickName.toString()){

                                    binding.UserUpdateEmailIssue.visibility = View.GONE
                                    binding.UserUpdatePassword1Issue.visibility = View.GONE
                                    binding.UserUpdateNickNameIssue.visibility = View.GONE
                                    NickNameDoubleCheck = true;
                                    a = a + 1

                                }

                            }

                            //패스워드만 수정했을때
                            if(!oldPassword.contains(binding.UserUpdatePassword1.text.toString())){
                                if( binding.UserUpdateNickName.text.toString() == oldNickName.toString()
                                    && binding.UserUpdateEmail.text.toString() == oldEmail.toString()){

                                    binding.UserUpdatePassword1.visibility = View.GONE
                                    NickNameDoubleCheck = true
                                    EmailDoubleCheck = true
                                    a = a + 1

                                }

                            }

                            //닉네임만 수정했을때
                            if(!oldNickName.contains(binding.UserUpdateNickName.text.toString())){
                                if( binding.UserUpdateEmail.text.toString() == oldEmail.toString()
                                    && binding.UserUpdatePassword1.text.toString() == oldPassword.toString()){

                                    binding.UserUpdateEmailIssue.visibility = View.GONE



                                    EmailDoubleCheck = true
                                    a = a + 1

                                }

                            }else{
                                if(getnicknamevalue != oldNickName && getnicknamevalue.toString().length > 0){
                                    binding.UserUpdateNickNameIssue.visibility = View.VISIBLE
                                    binding.UserUpdateNickNameIssue.setText("이미 존재하는 닉네임 입니다.").toString()

                                    NickNameDoubleCheck = false;
                                }




                            }








                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(applicationContext,
                            databaseError.message,
                            Toast.LENGTH_SHORT).show()
                    }
                })

            
            
            




            //이메일 비었는지 확인
            if (binding.UserUpdateEmail.text.toString().length === 0) {

                binding.UserUpdateEmailIssue.visibility = View.VISIBLE
                binding.UserUpdateEmailIssue.setText("이메일은 공란일 수 없습니다.").toString()

            }

            //이메일이 30글자를 초과 했을 때,
            if (getemailvalue.length  > 30) {
                binding.UserUpdateEmailIssue.visibility = View.VISIBLE
                binding.UserUpdateEmailIssue.setText("이메일이 30글자를 초과 했습니다.").toString()


            }

            //  이메일이 15글자 미만일 때,
            if (getemailvalue.length  < 15  && getemailvalue.length > 0) {
                binding.UserUpdateEmailIssue.visibility = View.VISIBLE
                binding.UserUpdateEmailIssue.setText("이메일은 최소 15자로 입력 해주세요.").toString()


            }



            //15글자가 넘고 30자 이하 면서, 이메일 형식이 아닐때,
            if (getemailvalue.length  >= 15 && !emailPatttern.matcher(getemailvalue.toString()).matches() && getemailvalue.length <= 30) {
                binding.UserUpdateEmailIssue.visibility = View.VISIBLE
                binding.UserUpdateEmailIssue.setText("이메일 형식으로 입력 해주세요.").toString()

            }



            //15글자가 넘고 30자 이하 면서 이메일 형식일때
            if (getemailvalue.length  >= 15 && emailPatttern.matcher(getemailvalue.toString()).matches() && getemailvalue.length <= 30) {
                binding.UserUpdateEmailIssue.visibility = View.GONE
                a = a + 1

            }











            //닉네임이 공백인 경우
            if (binding.UserUpdateNickName.text.length === 0 && getnicknamevalue != oldNickName) {
                binding.UserUpdateNickNameIssue.visibility = View.VISIBLE
                binding.UserUpdateNickNameIssue.setText("닉네임은 공란일 수 없습니다.").toString()

            }


            //  닉네임이 8글자를 초과 했을 때,
            if (getnicknamevalue.length  > 8) {
                binding.UserUpdateNickNameIssue.visibility = View.VISIBLE
                binding.UserUpdateNickNameIssue.setText("닉네임은 8글자 이하로 입력 해주세요.").toString()


            }

            //2글자 미만 일떄 ,
            if (getnicknamevalue.length  < 2 && getnicknamevalue.length  > 0  ) {
                binding.UserUpdateNickNameIssue.visibility = View.VISIBLE
                binding.UserUpdateNickNameIssue.setText("닉네임은 2글자 이상이여야 합니다.").toString()


            }


           
//            공백, 한글, 알파벳, 숫자 허용
//            닉네임이 특수 문자를 포함한 경우

            if (getnicknamevalue.length  >= 2 && !Pattern.matches("^[a-zA-Z0-9ㄱ-ㅣ가-힣]*$",getnicknamevalue) && getnicknamevalue.length  <= 8) {
                binding.UserUpdateNickNameIssue.visibility = View.VISIBLE
                binding.UserUpdateNickNameIssue.setText("닉네임은 영문과 숫자 한글 조합만 입력 가능합니다.").toString()


            }




            //  닉네임이 2글자가 넘고 8글자보다 작으며 특수문자가 포함이 안되어있을때(정상)
            if (getnicknamevalue.length  >= 2  && Pattern.matches("^[a-zA-Z0-9ㄱ-ㅣ가-힣]*$",getnicknamevalue) && getnicknamevalue.length  <= 8) {
                binding.UserUpdateNickNameIssue.visibility = View.GONE

                a = a+1
            }

















            //binding.UserUpdateNickName.text.isEmpty()
            //패스워드란이 비어있는 경우
            if (binding.UserUpdatePassword1.text.isEmpty()) {
                binding.UserUpdatePassword1Issue.visibility = View.VISIBLE
                binding.UserUpdatePassword1Issue.setText("패스워드는 공란일 수 없습니다.").toString()




            }

            //패스워드가 20글자를 초과 했을 때,
            if (getpasswordvalue.length  > 20) {
                binding.UserUpdatePassword1Issue.visibility = View.VISIBLE
                binding.UserUpdatePassword1Issue.setText("패스워드는 20글자 이하로 입력 해주세요.").toString()

            }

            //패스워드가 8글자 미만 일때,
            if (getpasswordvalue.length  < 8  && getpasswordvalue.length > 0) {
                binding.UserUpdatePassword1Issue.visibility = View.VISIBLE
                binding.UserUpdatePassword1Issue.setText("패스워드는 8자이상 입력 해주세요.").toString()


            }

            //패스워드 8~20글자중 특수문자,영문자,한글이 한번씩 들어가야함(정규표현식)
            if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.UserUpdatePassword1.text.toString())) {

                a = a + 1
            }




            //이메일 형식이 아닌경우,
            if (!emailPatttern.matcher(binding.UserUpdateEmail.text.toString().toString()).matches()) { //이메일 형식이 아닌경우
                Toast.makeText(this, "이메일 형식으로 입력 해주세요.", Toast.LENGTH_LONG).show()  // 비어있는 경우 "입력 해주세요" 메세지 출력


            }

//            // 이메일 형식인 경우,
//            if (emailPatttern.matcher(binding.UserUpdateEmail.text.toString().toString()).matches()) { //이메일 형식이 아닌경우
//
//               a=a+1
//
//            }





//            //정규표현식인 경우
//            if (Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", binding.UserUpdatePassword1.text.toString())) {
//                    a=a+1
//            }









            Log.d("EmailDoublecheck",EmailDoubleCheck.toString())
            Log.d("NickNameDoublecheck",NickNameDoubleCheck.toString())



           if(a >= 3  && EmailDoubleCheck === true && NickNameDoubleCheck === true) {


                //멤버변수에 값 전달

                this.Email = binding.UserUpdateEmail.text.toString()
                this.Password = binding.UserUpdatePassword1.text.toString()
                this.NickName = binding.UserUpdateNickName.text.toString()
                val newPassword = this.Password




                //데이터 Update()

               CustomerData.child("UserInfo").child(user100.toString()).child("UserEmail")
                   .setValue(this.Email).addOnCompleteListener(){
                       user!!.updateEmail(this.Email)
                           .addOnCompleteListener { task ->
                               if (task.isSuccessful) {

                               }
                   }



               CustomerData.child("UserInfo").child(user100.toString()).child("UserPassword")
                   .setValue(this.Password).addOnCompleteListener(){
                       user!!.updatePassword(newPassword)
                           .addOnCompleteListener { task ->
                               if (task.isSuccessful) {


                               }
                   }



                   }


                   }



               CustomerData.child("UserInfo").child(user100.toString()).child("UserNickName")
                   .setValue(this.NickName)

               val intent = Intent(this, MainActivity::class.java)
               intent.flags =
                   Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
               startActivity(intent)
               Toast.makeText(this, "정보가 변경 되었습니다. ", Toast.LENGTH_LONG).show()




            }



        }
        auth = Firebase.auth




    }
}









