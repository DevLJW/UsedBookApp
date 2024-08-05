package com.example.book.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.book.Board.BoardInsideActivity
import com.example.book.Board.BoardListLVAdapter
import com.example.book.Board.BoardModel
import com.example.book.Board.BoardWriteActivity
import com.example.book.R
import com.example.book.databinding.FragmentBoardBinding
import com.example.book.databinding.FragmentHomeBinding
import com.example.book.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.main_toolbar.*



class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding

    private val boardDataList = mutableListOf<BoardModel>() //수정가능한 리스트(MutableListOf)  Type이 BoardModel인 ArrayList로 초기화
    private val boardKeyList = mutableListOf<String>()
    private lateinit var boardRVAdapter: BoardListLVAdapter // BoardListLVAdapter 파일 형식

    private val TAG = BoardFragment::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false)

        getFBBoardDate() //리스너 등록할려고

        //마지막 실행 어댑터
        boardRVAdapter = BoardListLVAdapter(boardDataList) // BoardListLVAdapter의 boardList 생성자 값 인자로 전달
        binding.boardListView.adapter = boardRVAdapter  // 객체를 생성하고 연동한 속성을 연동


        binding.boardListView.setOnItemClickListener { parent, view, position, id -> //리스트뷰를 클릭했을때, 게시글 세부 페이지 이동

            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position]) //화면이 이동되면서 boardKeyList의 키값을 넘겨준다.
            startActivity(intent)
        }




        binding.writeBtn.setOnClickListener() {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)

        }


        binding.homeTab.setOnClickListener {
            it.findNavController().navigate(R.id.action_boardFragment_to_homeFragment)
        }


        binding.BookBtn.setOnClickListener() {
            it.findNavController().navigate(R.id.action_boardFragment_to_bookFragment)

        }

        binding.Test1.setOnClickListener() {
            it.findNavController().navigate(R.id.action_boardFragment_to_test1Fragment)
        }

        binding.Test2.setOnClickListener() {
            it.findNavController().navigate(R.id.action_boardFragment_to_test2Fragment)

        }


        return binding.root
    }


    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }


    fun getFBBoardDate() { //FireBase 게시판 데이터베이스를 가져오기

        val postListener = object : ValueEventListener {
            //콜백 함수
            override fun onDataChange(dataSnapshot: DataSnapshot) { //dataSnapshot : board 최상위 주소를 받고 DataSnapshot 객체형식으로 변환
                boardDataList.clear()

                for (dataModel in dataSnapshot.children) {  //boardref의 하위값들에 접근


                    val item = dataModel.getValue(BoardModel::class.java)


                    boardDataList.add(item!!) //!!널체크
                    boardKeyList.add(dataModel.key.toString()) //키값 넣기
                }
                boardKeyList.reverse()
                boardDataList.reverse() //게시글을 최근쓴글부터 상단으로 나오게하기

                boardRVAdapter.notifyDataSetChanged() //어댑터 동기화
                Log.d(TAG, "Hi")


            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.d(tag, "loadPost::onCancelled", databaseError.toException())
            }


        }


        //값의 변화가 있으면 실행되는 리스너 addValue 한번만 등록해놓으면 이벤트가 발생할때마다 실행
        FBRef.boardRef.addValueEventListener(postListener) // 처음에무조건 한번실행

        //onDataChange() 메서드를 사용하여 이벤트 발생 시점에 특정 경로에 있던 콘텐츠의 정적 스냅샷을 읽을 수 있습니다.
       // 이 메서드는 리스너가 연결될 때 한 번 트리거된 후 하위 요소를 포함한 데이터가 변경될 때마다 다시 트리거됩니다.
      // 하위 데이터를 포함하여 해당 위치의 모든 데이터를 포함하는 스냅샷이 이벤트 콜백에 전달됩니다. 데이터가 없는 경우 스냅샷은 exists() 호출 시
      // false를 반환하고 getValue() 호출 시 null을 반환합니다.


    }
}
