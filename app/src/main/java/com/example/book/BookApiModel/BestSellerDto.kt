package com.example.book.BookApiModel

import com.google.gson.annotations.SerializedName

data class BestSellerDto(  //BestSellerDto로 전체 api 데이터 가져오기

    @SerializedName("title") val title : String,
    @SerializedName("item") val books : List<BookModel>, //리스트 안에 데이터는 BookModel 형식으로 맵핑이된다.
)