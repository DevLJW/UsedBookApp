package com.example.book.fragments

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding

    private val boardDataList =mutableListOf<BoardModel>() //수정가능한 리스트(MutableListOf)  Type이 BoardModel인 ArrayList로 초기화
    private val boardKeyList = mutableListOf<String>()
    private lateinit var boardRVAdapter: BoardListLVAdapter // BoardListLVAdapter 파일 형식


    private val TAG = BoardFragment::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {


            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false)

            boardRVAdapter = BoardListLVAdapter(boardDataList)
            binding.boardListView.adapter = boardRVAdapter



            binding.boardListView.setOnItemClickListener { parent, view, position, id -> //리스트뷰를 클릭했을때, 게시글 세부 페이지 이동
//
                val intent = Intent(context, BoardInsideActivity::class.java)
                intent.putExtra("key",boardKeyList[position]) //화면이 이동되면서 boardKeyList의 키값을 넘겨준다.
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

            getFBBoardDate()
            return binding.root
        }


    fun getFBBoardDate() { //FireBase내 게시글 테이블 정보를 가져오는 함수

            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    boardDataList.clear()

                    for (dataModel in dataSnapshot.children) {


                        val item = dataModel.getValue(BoardModel::class.java)
               //         dataModel.key

                        boardDataList.add(item!!) //!!널체크
                        boardKeyList.add(dataModel.key.toString()) //키값 넣기
                    }
                    boardKeyList.reverse()
                    boardDataList.reverse() //게시글을 최근쓴글부터 상단으로 나오게하기

                    boardRVAdapter.notifyDataSetChanged()
                    Log.d(TAG, boardDataList.toString())


                }

                override fun onCancelled(databaseError: DatabaseError) {

                    Log.d(tag, "loadPost::onCancelled", databaseError.toException())
                }


            }

            FBRef.boardRef.addValueEventListener(postListener)


        }

    }
