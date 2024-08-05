package com.example.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.book.BookApiModel.BookModel
import com.example.book.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {

    private lateinit var  binding:ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {



       binding = ActivityBookDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val model = intent.getParcelableExtra<BookModel>("bookModel")
        binding.titletextview.text = model?.title.orEmpty()
        binding.descriptionTextView.text = model?.description.orEmpty()
        Glide.with(binding.coverImageView.context).load(model?.coverSmallUrl.orEmpty())
            .into(binding.coverImageView)


    }
}