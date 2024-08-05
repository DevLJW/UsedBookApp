package com.example.book.api


import com.example.book.BookApiModel.BestSellerDto
import com.example.book.BookApiModel.SearchBookDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call



interface BookService { //동작정의용 인터페이스


    @GET("/api/search.api?output=json") //책검색
    fun getBooksByName(
        @Query("key") apiKey: String,
        @Query("query") keyword: String,

    ): Call<SearchBookDto>



    @GET("/api/bestSeller.api?output=json&categoryId=100")  //베스트 셀러
    fun getBestSellerBooks(
        @Query("key") apiKey:String
    ):Call<BestSellerDto>




}