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
    private var isImageUpload = false
    private lateinit var writer : String


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


                        val key = FBRef.boardRef.push().key.toString() //푸쉬 키값 (게시글 생성 전 키값 생성),이미지 이름도 동일하게 하기위해
                        //key 게시글 랜덤키값
                        FBRef.boardRef.child(key).setValue(BoardModel(title, content, time, uid)) //생성자를 생성하고, 생성한 값을 DB에 저장
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

    //이미지 업로드 속성 함수
    private fun imageupload(key: String) {

        // Get the data from an ImageView as bytes

        val storage = Firebase.storage //스토리지 사용 선언 FireBaseStorage 클래스의 인스턴스를 반환
        val storageRef = storage.getReference() //스토리지서버 주소값 반환
        val mountainsRef = storageRef.child(key + ".jpg") //이미지 업로드 시, 이미지 이름 + jpg
        val imageView = binding.ImageBtn

        imageView.isDrawingCacheEnabled = true //캐시에 뷰의 이미지를 저장할지를 결정한다 --> 다음에 호출되는 View를 Bitmap으로 저장하기 위해서
        imageView.buildDrawingCache() //캐시를 사용한다고 설정이 되어있으면 실제 cache에 저장해주는 기능을 하는 함수
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap //이미지뷰에 있는 이미지형식을 BitmapDrawable 객체 타입으로 치환 후 getBitmap()함수에 접근
                                                                    //Bit맵 타입으로 이미지뷰를 반환해줌
                                                                    //ImageView.Drawable = 현재 ImageView에 할당된 Drawable을 가져옴
        val baos = ByteArrayOutputStream() //최대 32바이트 배열 출력 스트림 생성
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos) // 이미지 업로드 시, JPEG 타입, 화질 100, 최대 32바이트 스트림 크기로 지정,baos 출력스트림에 기록하겠다.
        val data = baos.toByteArray()  //32바이트 스트림 크기로 지정한 값이 들어있는 baos변수의 출력 스트림의 현재 크기를 복사하여 바이트배열 생성

        var uploadTask = mountainsRef.putBytes(data) //파이어베이스 스토리지서버 경로에 출력스트림에 있던 이미지를 업로드한다.


        uploadTask.addOnFailureListener {

        }.addOnSuccessListener { taskSnapshot ->

        }


    }

    //int requestCode : subActivity를 호출했던 startActivityForResult()의 두번째 인수값
    //int resultCode : 호출된 액티비티에서 설정한 성공(RESULT_OK)/실패(RESULT_CANCEL) 값
    //Intent data : 호출된 액티비티에서 저장한 값

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //갤러리 이미지를 열고 다시 글쓰기 화면으로 돌아왔을떄,
        super.onActivityResult(requestCode, resultCode, data)

            if (resultCode == RESULT_OK && requestCode == 100) {
                binding.ImageBtn.setImageURI((data?.data))
                Toast.makeText(this, "이미지 선택 완료", Toast.LENGTH_LONG).show()

            }
        }
    }

