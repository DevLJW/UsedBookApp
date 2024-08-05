package com.example.book.Chat

import androidx.recyclerview.widget.RecyclerView
import com.example.book.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.chat_right.view.*

class ChatRight(val msg : String) : Item<GroupieViewHolder>() { //유저 리스트 어댑터


    override fun getLayout(): Int {
        return R.layout.chat_right
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.rightmsg.text = msg
    }


}
