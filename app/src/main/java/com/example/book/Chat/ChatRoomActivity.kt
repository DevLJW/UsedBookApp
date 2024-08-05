package com.example.book.Chat

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.book.R
import com.example.book.utils.FBAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_chat_room.*
import kotlin.time.measureTimedValue

class ChatRoomActivity : AppCompatActivity() {

    private lateinit var  auth : FirebaseAuth
    val database = Firebase.database
    val DataBaseRootReference = database.getReference()
    val DataBaseMessageReference = database.getReference().child("Message")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        auth = FirebaseAuth.getInstance()

        val name: String? = intent.getStringExtra("name")
        val youUid: String? = intent.getStringExtra("youUid")
        val LoginUserName: String? = intent.getStringExtra("loginUserName")
        val myuid : String? = auth.uid



       // Log.d(TAG,youUid.toString())



        val readref = database.getReference().child("Message").child(myuid.toString()).child(youUid.toString())
        val adapter = GroupAdapter<GroupieViewHolder>();









//       DataBaseRootReference.child("Message").orderByChild("time").addListenerForSingleValueEvent(object : ValueEventListener { //DB 닉네임 전체 데이터 가져오기
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (datas in dataSnapshot.children) {
//
//                     val senduid = datas.child("myUid").value.toString() //보낸사람 uid
//                     val msg = datas.child("message").value.toString() //메세지
//                   // adapter.add(UserItem(datas.child("UserNickName").value.toString(),datas.child("UserUID").value.toString()))
//
//                    if(senduid!!.equals(myuid)){  //같으면 내가보낸 메세지라 오른쪽표시
//
//                        adapter.add(ChatRight(msg))
//
//                    } else{                 //* 다르면 왼쪽표시
//                        adapter.add(ChatLeft(msg))
//                    }
//
//
//                }
//
//               // RecylerView_list.adapter = adapter
//                recyclerViewchatroom.adapter = adapter
//
//
//            }
//
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
       // recyclerViewchatroom.adapter = adapter


       // DataBaseMessageReference.child(myuid.toString()).child(youUid.toString())

        val childEventListener = object :  ChildEventListener {

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                val model : ChatNewModel? = snapshot.getValue(ChatNewModel::class.java)
                val msg:String = model?.message.toString()
                val who:String? = model?.who.toString()




                if(who == "me"){
                        adapter.add(ChatRight(msg))
                }else{

                         adapter.add(ChatLeft(msg,LoginUserName.toString(),name.toString()))
                }




            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        }


        readref.addChildEventListener(childEventListener)
        recyclerViewchatroom.adapter = adapter


        imageButton.setOnClickListener(){

            val message:String = inputtext.text.toString()
            Log.d(TAG,LoginUserName.toString())








            //전송 데이터
            val Chat = ChatNewModel(myuid.toString(),youUid.toString(),message,FBAuth.getTime2().toString(),"me",LoginUserName.toString(),name.toString())
            DataBaseMessageReference.child(myuid.toString()).child(youUid.toString()).push().setValue(Chat)

            //수신 데이터
            val Chat_get = ChatNewModel(youUid.toString(),myuid.toString(),message,FBAuth.getTime2().toString(),"you",LoginUserName.toString(),name.toString()) //받은애들
            DataBaseMessageReference.child(youUid.toString()).child(myuid.toString()).push().setValue(Chat_get)
            inputtext.setText("")

//            DataBaseRootReference.child("UserInfo").child(user100.toString()).child("UserNickName").get()
//                .addOnSuccessListener { //Firebase 데이터 읽어오기
//
//
//                    val abc : String = it.value.toString()
//                    DataBaseMessageReference.child(myuid.toString()).child(youUid.toString()).push().setValue(abc.toString())//비동기이슈
//                    DataBaseMessageReference.child(youUid.toString()).child(myuid.toString()).push().setValue(name)
//
//                }



    //        val message:String = inputtext.text.toString()
    //        inputtext.setText("")

  //          val chat = ChatModel(myuid.toString(),youUid.toString(),message,FBAuth.getTime2().toString())

   //        DataBaseRootReference.child("Message").push().setValue(chat)

        }


    }
}