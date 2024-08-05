package com.example.book.Chat

import com.example.book.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.chat_left.view.*
import kotlinx.android.synthetic.main.chat_right.view.*
import kotlinx.android.synthetic.main.message_list_row.view.*

class ChatLeft(val msg:String, val send : String,val rep : String) : Item<GroupieViewHolder>() { //유저 리스트 어댑터


    override fun getLayout(): Int {
        return R.layout.chat_left
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.leftmsg.text= msg
        viewHolder.itemView.left_id.text= rep

    }




}