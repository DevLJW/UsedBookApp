package com.example.book.Board

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.book.MainActivity
import com.example.book.R
import com.example.book.databinding.ActivityBoardEditBinding
import com.example.book.fragments.BoardFragment
import com.example.book.utils.FBAuth
import com.example.book.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.lang.Exception


class BoardEditActivity : AppCompatActivity() {


    private lateinit var key: String

    private lateinit var binding: ActivityBoardEditBinding

    private val TAG = BoardEditActivity::class.java

    private lateinit var writeruid: String

    private var isImageUpload = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        //setContentView(R.layout.activity_board_edit)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit)
            //인텐트 : 화면넘기거나 데이터전달용
            //인텐트속성으로 태스크초기화같은걸 진행 할 수 있음
        key = intent.getStringExtra("key").toString()

        getboardDate(key)
        getImageData(key)


        binding.ImageBtn.setOnClickListener {   //이미지 버튼 클릭시

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
            isImageUpload = true


        }





        binding.editBtn.setOnClickListener {
            editBoardData(key)
            if(isImageUpload == true) {
                imageupload(key)
            }



        }




    }



    private fun editBoardData(key: String){
     FBRef.boardRef.child(key).setValue(BoardModel(binding.titleArea.text.toString(),binding.contentArea.text.toString(),FBAuth.getTime(),writeruid))
        Toast.makeText(this,"수정완료",Toast.LENGTH_LONG).show()
        finish()
        val intent = Intent(this, MainActivity::class.java)

        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)


}

    private fun imageupload(key : String){

        // Get the data from an ImageView as bytes

        val storage = Firebase.storage //스토리지 사용 선언
        val storageRef = storage.reference
        val mountainsRef = storageRef.child(key + ".jpg")
        val imageView = binding.ImageBtn

        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }


    }


    private fun getImageData(key : String) {
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key +".jpg")

        // ImageView in your Activity
        val imageViewFromFB = binding.ImageBtn

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if(task.isSuccessful){
                Glide.with(this).load(task.result).into(imageViewFromFB)
            }else {

            }

        })

    }



    private fun getboardDate(key : String){


        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                try {
                    val dataModel =
                        dataSnapshot.getValue(BoardModel::class.java) //보드모델형태로 데이터를 받을꺼다.

                    binding.titleArea.setText((dataModel!!.title))
                    binding.contentArea.setText((dataModel!!.content))
                    writeruid = dataModel.uid.toString()



                } catch (e: Exception) {


                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.w(ContentValues.TAG, "loadPost::onCancelled", databaseError.toException())
            }


        }

        FBRef.boardRef.child(key).addValueEventListener(postListener)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            binding.ImageBtn.setImageURI((data?.data))
        }
    }

}