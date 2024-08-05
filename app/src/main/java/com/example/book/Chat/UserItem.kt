package com.example.book.Chat

import com.example.book.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.message_list_row.view.*

class UserItem(val name:String, val uid:String) : Item<GroupieViewHolder>() { //유저 리스트 어댑터


    override fun getLayout(): Int {
        return R.layout.message_list_row
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
       viewHolder.itemView.nametext.text = name
    }




}