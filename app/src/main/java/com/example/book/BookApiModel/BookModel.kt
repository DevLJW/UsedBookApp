package com.example.book.BookApiModel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize //직렬화
data class BookModel( //받을 데이터 형식


    @SerializedName("itemId") val id: Long, //api에선 itemid으로 내려주지만 데이터 클래스에선 id로 치환
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String


): Parcelable
