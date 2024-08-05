package com.example.book.fragments
import android.app.AlertDialog
import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.book.MainActivity
import com.example.book.MapActivity
import com.example.book.MapActivity.Companion.SEARCH_RESULT_EXTRA_KEY
import com.example.book.MapAdapter.SearchRecylearAdapter
import com.example.book.MapModel.LocationLatLngEntity
import com.example.book.MapModel.POI
import com.example.book.MapModel.POIS
import com.example.book.MapModel.SearchResultEntity
import com.example.book.R
import com.example.book.UserUpadateActivity
import com.example.book.databinding.FragmentTest2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.book.auth.IntroActivity
import com.example.book.utils.FBAuth
import com.example.book.utils.RetrofitUtil
import com.example.book.utils.UserUpadeData
import com.google.firebase.database.ktx.database
import kotlinx.android.synthetic.main.fragment_board.*
import kotlinx.android.synthetic.main.fragment_test2.searchBarInputView
import kotlinx.coroutines.*

import java.lang.Exception
import kotlin.coroutines.CoroutineContext


public class test2Fragment : Fragment(), CoroutineScope {

    private lateinit var binding: FragmentTest2Binding
    private lateinit var auth: FirebaseAuth
    private lateinit var job: Job
    private lateinit var adapter: SearchRecylearAdapter

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job




    val user100 = Firebase.auth.currentUser?.uid //현재 로그인한 사용자
    val database = Firebase.database       //읽고쓰기위한 변수생성
    val CustomerData = database.getReference() //데이터베이스 주소값 가져오기


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        job = Job()


    }

    private fun initAdapter() {
        adapter = SearchRecylearAdapter()
    }


    private fun initViews() = with(binding) {
        emptyResultTextView.isVisible = false
        recyclerView.adapter = adapter
    }

    private fun bindViews() = with(binding) {
        searchButton.setOnClickListener {
            searchKeyword(searchBarInputView.text.toString())
        }
    }

    private fun initData() {
        adapter.notifyDataSetChanged()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test2, container, false)


        auth = Firebase.auth

        //지도 코드


        initAdapter()
        initViews()
        bindViews()
        initData()










        //화면이동용
        binding.homeTab.setOnClickListener() {
            it.findNavController().navigate(R.id.action_test2Fragment_to_homeFragment)
        }

        binding.boardTab.setOnClickListener {
            it.findNavController().navigate(R.id.action_test2Fragment_to_boardFragment)
        }


        binding.BookBtn.setOnClickListener() {
            it.findNavController().navigate(R.id.action_test2Fragment_to_bookFragment)

        }



        binding.Test1.setOnClickListener() {
            it.findNavController().navigate(R.id.action_test2Fragment_to_test1Fragment)

        }
        return binding.root
    }

    private fun searchKeyword(keywordString: String) {
        launch(coroutineContext) {
            try {
                withContext(Dispatchers.IO) {
                    val response = RetrofitUtil.apiService.getSearchLocation(
                        keyword = keywordString
                    )
                    if (response.isSuccessful) {

                        val body = response.body()
                        withContext(Dispatchers.Main) {



                            body?.let { searchResponseSchema ->



                                    setData(searchResponseSchema.searchPoiInfo.pois)





                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "검색하는 과정에서 에러가 발생했습니다. : ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setData(pois: POIS) {
        val dataList = pois.poi.map {
            Log.d("123",it.name.toString())

               it.name.toString() === searchBarInputView.text.toString()
                SearchResultEntity(
                        fullAdress = makeMainAdress(it),
                        name = it.name ?: "",
                        locationLatLng = LocationLatLngEntity(it.noorLat, it.noorLon)
                    )
















        }


        adapter.setSearchResultList(dataList) {
            startActivity(
                Intent(context, MapActivity::class.java).apply {
                    putExtra(SEARCH_RESULT_EXTRA_KEY, it)
                }
            )
        }


    }


    private fun makeMainAdress(poi: POI): String =
        if (poi.secondNo?.trim().isNullOrEmpty()) {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    poi.firstNo?.trim()
        } else {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    (poi.firstNo?.trim() ?: "") + " " +
                    poi.secondNo?.trim()
        }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
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





















