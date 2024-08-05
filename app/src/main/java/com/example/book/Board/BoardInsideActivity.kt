package com.example.book.Board

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.book.R
import com.example.book.comment.commentLVAdapter
import com.example.book.comment.commentmodel
import com.example.book.databinding.ActivityBoardInsideBinding
import com.example.book.utils.FBAuth
import com.example.book.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.lang.Exception
import java.util.*

class BoardInsideActivity : AppCompatActivity() {

    private val Tag = BoardInsideActivity::class.java
    private lateinit var  binding : ActivityBoardInsideBinding
    private lateinit var key  : String


    private val commentDataList =mutableListOf<commentmodel>()
    private  lateinit var commentAdapter : commentLVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        binding.boardsetbtn.setOnClickListener {


        }
       





        key = intent.getStringExtra("key").toString()
        getboardDate(key)

        getImageData(key)

        binding.commentBtn.setOnClickListener(){
            insertcomment(key)


        }

        commentAdapter = commentLVAdapter(commentDataList)
        binding.commentLV.adapter = commentAdapter
        getcommentData(key)





    }

    fun getcommentData(key : String){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                    commentDataList.clear()
                for (dataModel in dataSnapshot.children) {
                val item = dataModel.getValue(commentmodel::class.java)
                    commentDataList.add(item!!)

                }
                commentAdapter.notifyDataSetChanged() //동기화
            }







            override fun onCancelled(databaseError: DatabaseError) {

                Log.w(TAG, "loadPost::onCancelled", databaseError.toException())
            }


        }

        FBRef.commentRef.child(key).addValueEventListener(postListener)
    }

    fun insertcomment(key : String){

            FBRef.commentRef.child(key).push().setValue(commentmodel(binding.commentArea.text.toString(),FBAuth.getTime()))
            Toast.makeText(this,"댓글 입력 완료", Toast.LENGTH_LONG).show()
            binding.commentArea.setText("")


    }




    private fun showDialog(){

    val mDialogView = LayoutInflater.from(this).inflate(R.layout.boardset, null)
    val mBuilder = AlertDialog.Builder(this)
        .setView(mDialogView)
        .setTitle("게시글 수정 삭제")
    val alertDialog = mBuilder.show()



    alertDialog.findViewById<Button>(R.id.updatebtn)?.setOnClickListener() { //수정버튼


        val intent = Intent(this,BoardEditActivity::class.java)
        intent.putExtra("key",key) //키값 넘기기
        startActivity(intent)

    }

    alertDialog.findViewById<Button>(R.id.deletebtn)?.setOnClickListener() { // 삭제버튼

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.boarddeleterequest, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("게시글 삭제")
        val alertDialog = mBuilder.show()

        alertDialog.findViewById<Button>(R.id.realdeletebtn)?.setOnClickListener() {
            
            FBRef.boardRef.child(key).removeValue() //게시글 데이터베이스 값 삭제
            Toast.makeText(this,"게시글 삭제가 완료 되었습니다.", Toast.LENGTH_LONG).show()
            ImageDelete(key) //게시글에 업로드된 이미지 삭제
            FBRef.commentRef.child(key).removeValue() //게시글에 달린 댓글 삭제
            finish()

        }

        alertDialog.findViewById<Button>(R.id.nobtn)?.setOnClickListener() { //아니오
            alertDialog.cancel()

        }



    }
}


    private fun ImageDelete(key : String){

        val storage = Firebase.storage
        val storageRef = storage.reference

        // Create a reference to the file to delete
        val desertRef = storageRef.child(key +".jpg") //이미지 업로드시 사용자 key 값 + .jpg이름으로 업로드

        // Delete the file
        desertRef.delete().addOnSuccessListener { //이벤트리스너 이미지 삭제 성공시 멘트작성

        }.addOnFailureListener {//이벤트리스너 이미지 삭제 실패시 멘트작성

        }


    }


    private fun getboardDate(key : String){


            val postListener = object : ValueEventListener { //변수에 객체형식으로  ValueEventListener인터페이스 상속 받기
                override fun onDataChange(dataSnapshot: DataSnapshot) { //보드테이블에 있는 컬럼 가져오기(dataSnapshot 변수에 dataSnapshot 객체 타입으로

                    try {
                        val dataModel = dataSnapshot.getValue(BoardModel::class.java) //보드모델 클래스 파일의 형식으로 데이터를 전달한다.

                       binding.titleArea.text = dataModel!!.title  //데이터모델 클래스에 있는 데이터를 this text에 값 대입
                       binding.textArea.text = dataModel!!.content
                        binding.timeArea.text = dataModel!!.time

                        val myuid = FBAuth.getUid() //파이어베이스 현재 로그인한 사용자의 uid값 가져오는 함수
                        val writeruid = dataModel.uid // 데이터모델 클래스에 있는 UID값 가져오기
                        if(myuid.equals((writeruid))){ //글쓴이와 현재로그인한사용자의 uid값이 같을시,
                            binding.boardsetbtn.isVisible = true //수정버튼 활성화

                        }else{
                            Log.d("123","내가쓴글아님")
                        }



                    } catch (e: Exception) {


                    }
                }

                override fun onCancelled(databaseError: DatabaseError) { //데이터베이스 오류시 출력되는 글

                    Log.w(TAG, "loadPost::onCancelled", databaseError.toException())
                }


            }

            FBRef.boardRef.child(key).addValueEventListener(postListener) // 게시판 리스너 호출


        }




    private fun getImageData(key : String) {
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key +".jpg") //최상위 주소 바로아래에 key값 + .jpg이름으로 이미지 저장

        // ImageView in your Activity
        val imageViewFromFB = binding.getImageArea //xml파일에 있는 이미지를 this페이지에 대입

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful){
                binding.getImageArea.isVisible = true
                Glide.with(this).load(task.result).into(imageViewFromFB)
            }else {
                binding.getImageArea.isVisible = false //이미지가 없을때 여백 없애기
            }

        })

    }



}