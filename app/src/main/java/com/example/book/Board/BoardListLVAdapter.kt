package com.example.book.Board

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.book.R
import com.example.book.utils.FBAuth

//데이터를 입력받아 View로 만들어주는 역할

class BoardListLVAdapter (val boardList : MutableList<BoardModel>) : BaseAdapter() {


    override fun getCount(): Int { //데이터 갯수
      return boardList.size
    }

    override fun getItem(position: Int): Any { //인자로 받은 위치의 데이터반환
       return boardList[position]
    }

    override fun getItemId(position: Int): Long {  //인자로 받은 위치의 데이터 id 구분자 반환
       return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View { //인자로 받은 위치의 데이터가 화면에 표시될 뷰 반환
     var converView = convertView

        if(converView == null) {        // 기존에 화면에 그렸던 뷰가 존재하지않으면 null, 이전에 회면에 그렸다가 보이지 않게 되면 null이 아닌값

            converView = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item,parent,false)
        }

        val title = converView?.findViewById<TextView>(R.id.titleArea)
        title!!.text = boardList[position].title



        val content = converView?.findViewById<TextView>(R.id.contentArea)
        content!!.text = boardList[position].content

        val time = converView?.findViewById<TextView>(R.id.timeArea)
        time!!.text = boardList[position].time



        return converView!!
    }
}