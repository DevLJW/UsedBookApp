package com.example.book.fragments

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book.BookApiModel.BestSellerDto
import com.example.book.BookApiModel.SearchBookDto
import com.example.book.BookDetailActivity
import com.example.book.R
import com.example.book.api.BookService
import com.example.book.bookAdapter.BookAdapter
import com.example.book.databinding.FragmentBoardBinding
import com.example.book.databinding.FragmentBookBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BookFragment : Fragment() {
        private lateinit var binding : FragmentBookBinding
        private lateinit var adapter: BookAdapter
        private lateinit var bookService:BookService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false)



            adapter = BookAdapter(itemClickedListener = {
                val intent = Intent(context,BookDetailActivity::class.java)
                intent.putExtra("bookModel",it)//클래스 자체를 넘기기
                startActivity(intent)

            })

            binding.bookRecylerView.layoutManager = LinearLayoutManager(context)
            binding.bookRecylerView.adapter = adapter




        val retrofit = Retrofit.Builder().baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create()).build() //레트로핏 생성

        val bookService = retrofit.create((BookService::class.java)) //북서비스 구현

        bookService.getBestSellerBooks("8BD143FFBE16FE933788C615A7B40BBC2432B41A69527A9791DAB3B9D228A1B4") //api key 입력
            .enqueue(object : Callback<BestSellerDto> {


                override fun onResponse(
                    call: Call<BestSellerDto>,
                    response: Response<BestSellerDto>
                ) { //성공처리
                    if (response.isSuccessful.not()) {
                        return
                    }

                    //Body == BestSellerDto 형식의 데이터가 들어있음
                    response.body()?.let {

                        Log.d("Tag", it.toString())

                        it.books.forEach() { book ->
                            Log.d("Tag", book.toString())
                        }

                        adapter.submitList(it.books)

                    }


                }


                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })



        fun search(keyword:String){

            bookService.getBooksByName(getString(R.string.interparkAPIKEY),keyword)

                .enqueue(object : Callback<SearchBookDto> {


                    override fun onResponse(
                        call: Call<SearchBookDto>,
                        response: Response<SearchBookDto>
                    ) { //성공처리
                        if (response.isSuccessful.not()) {
                            return
                        }

                        adapter.submitList(response.body()?.books.orEmpty())
                        response.body()?.let {

                            Log.d("Tag", it.toString())

                            it.books.forEach() { book ->
                                Log.d("Tag", book.toString())
                            }



                        }


                    }


                    override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })



        }

                binding.bookSearch.setOnKeyListener { v,keyCode,event ->

                    if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN) {
                        search(binding.bookSearch.text.toString())
                        return@setOnKeyListener true
                    }

                    return@setOnKeyListener false

                    }







        binding.homeTab.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookFragment_to_homeFragment)
        }



        binding.boardTab.setOnClickListener() {
            it.findNavController().navigate(R.id.action_bookFragment_to_boardFragment)

        }

        binding.Test1.setOnClickListener() {
            it.findNavController().navigate(R.id.action_bookFragment_to_test1Fragment)
        }

        binding.Test2.setOnClickListener() {
            it.findNavController().navigate(R.id.action_bookFragment_to_test2Fragment)

        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}