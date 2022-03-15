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
            showDialog()

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
            
            FBRef.boardRef.child(key).removeValue()
            ImageDelete(key)
            FBRef.commentRef.child(key).removeValue()
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
        val desertRef = storageRef.child(key +".jpg")

        // Delete the file
        desertRef.delete().addOnSuccessListener {
            Toast.makeText(this,"게시글 삭제가 완료 되었습니다.",Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this,"게시글 삭제가 완료 되었습니다.",Toast.LENGTH_LONG).show()
        }


    }


    private fun getboardDate(key : String){


            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    try {
                        val dataModel =
                            dataSnapshot.getValue(BoardModel::class.java) //보드모델형태로 데이터를 받을꺼다.

                        binding.titleArea.text = dataModel!!.title
                        binding.textArea.text = dataModel!!.content
                        binding.timeArea.text = dataModel!!.time

                        val myuid = FBAuth.getUid()
                        val writeruid = dataModel.uid
                        if(myuid.equals((writeruid))){
                            binding.boardsetbtn.isVisible = true

                        }else{
                            Log.d("123","내가쓴글아님")
                        }



                    } catch (e: Exception) {


                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {

                    Log.w(TAG, "loadPost::onCancelled", databaseError.toException())
                }


            }

            FBRef.boardRef.child(key).addValueEventListener(postListener)


        }




    private fun getImageData(key : String) {
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key +".jpg")

        // ImageView in your Activity
        val imageViewFromFB = binding.getImageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful){
                binding.getImageArea.isVisible = false
              //  Glide.with(this).load(task.result).into(imageViewFromFB)
            }else {
                binding.getImageArea.isVisible = false //이미지가 없을때 여백 없애기
            }

        })

    }



}