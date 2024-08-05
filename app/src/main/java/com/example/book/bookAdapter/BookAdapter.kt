package com.example.book.bookAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book.BookApiModel.BookModel
import com.example.book.databinding.ItemBookBinding

//뷰홀더 : 미리만들어진 뷰를 홀딩
class BookAdapter(private val itemClickedListener:(BookModel) -> Unit): ListAdapter<BookModel,BookAdapter.BookItemViewHolder>(diffUtil){

       inner class BookItemViewHolder(private val binding : ItemBookBinding): RecyclerView.ViewHolder(binding.root){//레이아웃 item_book파일 ItemBookBinding

              fun bind(bookModel: BookModel){

                     binding.titletextview.text= bookModel.title
                     binding.descritionTextView.text = bookModel.description
                     binding.root.setOnClickListener {

                            itemClickedListener(bookModel)
                     }

                     Glide.with(binding.coverImageView.context).load(bookModel.coverSmallUrl).into(binding.coverImageView)




              }
}


       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder { //미리만들어진 뷰홀더가 없을경우 새로 생성하는 함수

              return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))


       }



       override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) { //뷰홀더가 뷰에 그려지기 됬을때 데이터를 바인딩해주는 함수
              holder.bind(currentList[position])


       }


       companion object{
              val diffUtil = object : DiffUtil.ItemCallback<BookModel>(){


                     override fun areItemsTheSame(oldItem: BookModel, newItem: BookModel): Boolean {
                            return oldItem == newItem
                     }

                     override fun areContentsTheSame(
                            oldItem: BookModel,
                            newItem: BookModel
                     ): Boolean {
                         return oldItem.id == newItem.id
                     }

              }

       }
}