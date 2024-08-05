package com.example.book.BookApiModel

import com.google.gson.annotations.SerializedName

data class SearchBookDto ( //책검색 전체읽는 DTO


    @SerializedName("title") val title : String,
    @SerializedName("item") val books : List<BookModel>,


        )
