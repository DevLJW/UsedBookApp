package com.example.book.fragments
import android.app.AlertDialog
import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.book.MainActivity
import com.example.book.R
import com.example.book.UserUpadateActivity
import com.example.book.databinding.FragmentTest2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.book.auth.IntroActivity
import com.example.book.utils.FBAuth
import com.example.book.utils.UserUpadeData
import com.google.firebase.database.ktx.database
import kotlinx.android.synthetic.main.fragment_board.*


public class test2Fragment : Fragment() {

    private lateinit var binding: FragmentTest2Binding
    private lateinit var auth: FirebaseAuth


    val user100 = Firebase.auth.currentUser?.uid //현재 로그인한 사용자
    val database = Firebase.database       //읽고쓰기위한 변수생성
    val CustomerData = database.getReference() //데이터베이스 주소값 가져오기


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test2, container, false)





        binding.UserSignOut.setOnClickListener() { //로그아웃

            auth = Firebase.auth

            auth.signOut() //파이어베이스 인증 해제(로그아웃)

            val intent = Intent(context, IntroActivity::class.java) //메인화면으로 이동
            //intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) //기존에 쌓였던 Activity 제거
            startActivity(intent)

            Toast.makeText(context, "로그아웃 성공", Toast.LENGTH_LONG).show()

        }




        binding.UserUpdate.setOnClickListener() {

            var ConTextInfo = context


            //  UserInfog.UserInfoUpdate()
            if (ConTextInfo != null) { //context정보 널체크

                // UserInfog.TextDialog(ConTextInfo)

                var dblog =
                    CustomerData.child("UserInfo").child(user100.toString())
                        .child("UserPassword")
                        .toString()

                val mDialogView =
                    LayoutInflater.from(ConTextInfo as Context?)
                        .inflate(R.layout.passwordinput, null)
                val mBuilder = AlertDialog.Builder(ConTextInfo)
                    .setView(mDialogView)
                    .setTitle("회원정보 수정여부")
                val alertDialog = mBuilder.show()



                alertDialog.findViewById<Button>(R.id.pwdcheck1btn)
                    .setOnClickListener() { //확인 버튼 클릭 시,

                        alertDialog.dismiss()
                        val intent = Intent(context, UserUpadateActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    }


                alertDialog.findViewById<Button>(R.id.pwdcheck2btn).setOnClickListener() {

                    alertDialog.cancel()


                }


            }
        }







        binding.UserDelete.setOnClickListener() { //회원탈퇴


            val user = Firebase.auth.currentUser!! //인증삭제 변수
            val user100 = Firebase.auth.currentUser?.uid //현재 로그인한 사용자
            val database = Firebase.database       //읽고쓰기위한 변수생성
            val CustomerData = database.getReference() //데이터베이스 주소값 가져오기

            val mDialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(context)
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
                            .child("UserPassword").removeValue()
                        CustomerData.child("UserInfo").child(user100.toString())
                            .child("UserPhoneNumber").removeValue()
                        CustomerData.child("UserInfo").child(user100.toString())
                            .child("UserUID")
                            .removeValue()

                      //  CustomerData.child("board").child()
                         //   .child("UserUID")
                           // .removeValue()






                        val intent = Intent(context, IntroActivity::class.java) //메인화면으로 이동
                        //intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) //기존에 쌓였던 Activity 제거
                        startActivity(intent)


                        Toast.makeText(context, "회원탈퇴 성공", Toast.LENGTH_LONG).show()


                    }
                }


            }


            alertDialog.findViewById<Button>(R.id.nobtn).setOnClickListener() {
                alertDialog.cancel()

            }


        }
        auth = Firebase.auth


        //화면이동용
        binding.homeTab.setOnClickListener() {
            it.findNavController().navigate(R.id.action_test2Fragment_to_homeFragment)
        }

        binding.boardTab.setOnClickListener {
            it.findNavController().navigate(R.id.action_test2Fragment_to_boardFragment)
        }


        binding.BookBtn.setOnClickListener() {
            it.findNavController().navigate(R.id.action_test2Fragment_to_bookFragment)

        }



        binding.Test1.setOnClickListener() {
            it.findNavController().navigate(R.id.action_test2Fragment_to_test1Fragment)

        }
        return binding.root
    }




}




















