package com.example.book.Chat

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.book.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_chat_list.*



class Chat_List : AppCompatActivity() { //채팅리스트(사람) 화면


    val database = Firebase.database       //읽고쓰기위한 변수생성
    val CustomerData = database.getReference().child("UserInfo")//데이터베이스 주소값 가져오기

    lateinit var stringtest1 : String

    val user100 = Firebase.auth.currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

        val adapter = GroupAdapter<GroupieViewHolder>()







        CustomerData.addListenerForSingleValueEvent(object : ValueEventListener { //DB 닉네임 전체 데이터 가져오기
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (datas in dataSnapshot.children) {
                  //  val name = datas.child("UserNickName").value.toString()

                    adapter.add(UserItem(datas.child("UserNickName").value.toString(),datas.child("UserUID").value.toString()))




                }

                RecylerView_list.adapter = adapter
            }





            override fun onCancelled(databaseError: DatabaseError) {}
        }) // 비어있는 경우 "입력 해주세요" 메세지 출력

            adapter.setOnItemClickListener { item, view ->

                CustomerData.child(user100.toString()).child("UserNickName").get()
                    .addOnSuccessListener { //Firebase 데이터 읽어오기

                        val UserName : String = it.value.toString() //현재 로그인한 사용자 이름

                        val name:String = (item as UserItem).name
                        val uid:String =  (item as UserItem).uid
                        val intent = Intent(this,ChatRoomActivity::class.java)
                        intent.putExtra("name",name)
                        intent.putExtra("youUid",uid)
                        intent.putExtra("loginUserName",UserName)
                        startActivity(intent)

//                        binding.alldata.setText(it.value.toString())
//                        this.alldata = it.value.toString()

                    }



            }
    }



}
