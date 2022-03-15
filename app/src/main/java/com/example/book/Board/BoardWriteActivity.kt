package com.example.book.Board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import com.example.book.R
import com.example.book.databinding.ActivityBoardWriteBinding
import com.example.book.utils.FBAuth
import com.example.book.utils.FBRef
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

//글쓰기 페이지
@Suppress("DEPRECATION")
class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardWriteBinding
    val user = Firebase.auth.currentUser
    val user100 = Firebase.auth.currentUser?.uid //현재 로그인한 사용자
    val database = Firebase.database       //읽고쓰기위한 변수생성
    val CustomerData = database.getReference() //데이터베이스 주소값 가져오기

    private var isImageUpload = false


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)





        binding.writeBtn.setOnClickListener() { //글쓰기 버튼


            var goto = true
            val title = binding.titleArea.text.toString() //제목
            val content = binding.contentArea.text.toString() //내용


            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            //테이블 형태
            //타이틀,콘텐츠,uid,time
            //push() : 랜덤값이 생성(보드키값으로 사용예정)

            if (title.isEmpty()) {
                goto = false
                Toast.makeText(this, "제목란을 입력 해주세요.", Toast.LENGTH_LONG).show()
            }

            if (content.isEmpty()) {
                goto = false
                Toast.makeText(this, "내용란을 입력 해주세요.", Toast.LENGTH_LONG).show()
            }


            if (goto) { //goto 값이 true 시 이동


                val key =
                    FBRef.boardRef.push().key.toString() //푸쉬 키값 (게시글 생성 전 키값 생성),이미지 이름도 동일하게 하기위해

                FBRef.boardRef.child(key).setValue(BoardModel(title, content, time, uid))
                Toast.makeText(this, "게시글 작성 완료", Toast.LENGTH_LONG).show()


                if (isImageUpload == true) {
                    imageupload(key)
                }
                finish()


            }
        }


        binding.ImageBtn.setOnClickListener {   //이미지 업로드 버튼 클릭 시 갤러리 화면을 가져와 주는 코드,
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
            isImageUpload = true


        }

    }

    //이미지 업로드 함수
    private fun imageupload(key: String) {

        // Get the data from an ImageView as bytes

        val storage = Firebase.storage //스토리지 사용 선언
        val storageRef = storage.reference //스토리지 주소
        val mountainsRef = storageRef.child(key + ".jpg") //이미지 업로드 시, 이미지 이름 + jpg
        val imageView = binding.ImageBtn

        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()


        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {

        }.addOnSuccessListener { taskSnapshot ->

        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            if (resultCode == RESULT_OK && requestCode == 100) {
                binding.ImageBtn.setImageURI((data?.data))
                Toast.makeText(this, "이미지 선택 완료", Toast.LENGTH_LONG).show()

            }
        }
    }

