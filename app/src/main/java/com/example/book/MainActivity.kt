package com.example.book

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.book.auth.IntroActivity
import com.example.book.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_drawer_header.*

import kotlinx.android.synthetic.main.main_toolbar.*
import com.xwray.groupie.GroupieAdapter as GroupieAdapter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener { 
    
    //AppCompatActivity,OnNavigationItemSelectedListener 클래스를 상속받으므로 2개의 타입이 동시에 됨

    private lateinit var auth: FirebaseAuth //항상 초기화//Firebase 인증 인스턴스를 선언(공식문서기재)


    private lateinit var binding: ActivityMainBinding
    lateinit var UserEmail: TextView
    lateinit var UserNickName: TextView

    val userUid = Firebase.auth.currentUser?.uid //현재 로그인한 사용자
    val database = Firebase.database       //읽고쓰기위한 변수생성
    val DataBaseRoot = database.getReference() //데이터베이스 주소값 가져오기

    var  useremail1 : String? = null
    var  userpass1 : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        setSupportActionBar(main_layout_toolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.myinfo) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게



        main_navigationView.setNavigationItemSelectedListener(this)

    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼

                UserEmail = findViewById(R.id.useremail)
                UserNickName = findViewById(R.id.userid)


                DataBaseRoot.child("UserInfo").child(userUid.toString()) .child("UserEmail").get().addOnSuccessListener {
                    UserEmail.setText(it.value.toString())
                    useremail1 = it.value.toString()


                }.addOnFailureListener{

                }


                DataBaseRoot.child("UserInfo").child(userUid.toString()) .child("UserNickName").get().addOnSuccessListener {
                    UserNickName.setText(it.value.toString())



                }.addOnFailureListener{

                }






                DataBaseRoot.child("UserInfo").child(userUid.toString()) .child("UserPassword").get().addOnSuccessListener {

                    userpass1 = it.value.toString()

                }.addOnFailureListener{

                }









                main_drawer_layout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기



            }

        }

        return super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){


            R.id.first-> { //회원정보수정
                
                util(useremail1.toString(),userpass1.toString())




                //  UserInfog.UserInfoUpdate()
                if (this != null) { //context정보 널체크

                    // UserInfog.TextDialog(ConTextInfo)

                  /*  var dblog =
                        DataBaseRoot.child("UserInfo").child(userUid.toString())
                            .child("UserPassword")
                            .toString()
                            */


                    val mDialogView =
                        LayoutInflater.from(this as Context?)
                            .inflate(R.layout.passwordinput, null)
                    val mBuilder = AlertDialog.Builder(this)
                        .setView(mDialogView)
                        .setTitle("회원정보 수정여부")
                    val alertDialog = mBuilder.show()



                    alertDialog.findViewById<Button>(R.id.pwdcheck1btn)
                        .setOnClickListener() { //확인 버튼 클릭 시,

                            alertDialog.dismiss()
                            val intent = Intent(this, UserUpadateActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)

                        }


                    alertDialog.findViewById<Button>(R.id.pwdcheck2btn).setOnClickListener() {

                        alertDialog.cancel()


                    }
                }

            }
            R.id.second-> { //회원탈퇴

                util(useremail1.toString(),userpass1.toString())

                val user = Firebase.auth.currentUser!! //인증삭제 변수
                val user100 = Firebase.auth.currentUser?.uid //현재 로그인한 사용자
                val database = Firebase.database       //읽고쓰기위한 변수생성
                val CustomerData = database.getReference() //데이터베이스 주소값 가져오기

                val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("회원탈퇴 여부")
                val alertDialog = mBuilder.show()



                alertDialog.findViewById<Button>(R.id.okbtn).setOnClickListener() {

                    user.delete().addOnCompleteListener { task ->
                        if (task.isSuccessful) {


                            CustomerData.child("UserInfo").child(user100.toString())
                                .child("UserAddress").removeValue()
                            CustomerData.child("UserInfo").child(user100.toString())
                                .child("UserEmail")
                                .removeValue()
                            CustomerData.child("UserInfo").child(user100.toString())
                                .child("UserNickName")
                                .removeValue()
                            CustomerData.child("UserInfo").child(user100.toString())
                                .child("UserPassword").removeValue()
                            CustomerData.child("UserInfo").child(user100.toString())
                                .child("UserPhoneNumber").removeValue()
                            CustomerData.child("UserInfo").child(user100.toString())
                                .child("UserUID")
                                .removeValue()

                            //  CustomerData.child("board").child()
                            //   .child("UserUID")
                            // .removeValue()






                            val intent = Intent(this, IntroActivity::class.java) //메인화면으로 이동
                            //intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) //기존에 쌓였던 Activity 제거
                            startActivity(intent)


                            Toast.makeText(this, "회원탈퇴 성공", Toast.LENGTH_LONG).show()


                        }
                    }


                }


                alertDialog.findViewById<Button>(R.id.nobtn).setOnClickListener() {
                    alertDialog.cancel()

                }



            }

            R.id.three-> {

                auth = Firebase.auth //항상 초기화
                auth.signOut()
                val intent = Intent(this, IntroActivity::class.java) //메인화면으로 이동
                intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) //기존에 쌓였던 Activity 제거
                startActivity(intent)
                Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_LONG).show()

            }

        }
        return false
    }

    fun util(ID:String,password:String){

        val user = Firebase.auth.currentUser!!


        //firebase 같은경우 로그인 후 1시간 짜리 토큰을 발급 받음
        // 회원정보수정,회원정보탈퇴,로그아웃 등 보안에 민감한 작업들은 실행하기전에 토큰이 유효한지 확인하고 유효하면 실행, 비유효시 재발급


        val credential = EmailAuthProvider.getCredential(ID, password)


        user.reauthenticate(credential)
            .addOnCompleteListener {  Toast.makeText(this,"재인증성공",Toast.LENGTH_SHORT).show()}
    }


    override fun onBackPressed() { //뒤로가기 처리
        if(main_drawer_layout.isDrawerOpen(GravityCompat.START)){
            main_drawer_layout.closeDrawers()
            // 테스트를 위해 뒤로가기 버튼시 Toast 메시지
            Toast.makeText(this,"back btn clicked",Toast.LENGTH_SHORT).show()
        } else{
            super.onBackPressed()
        }
    }






  






}


