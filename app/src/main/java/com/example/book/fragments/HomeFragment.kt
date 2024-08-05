package com.example.book.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.book.MainActivity
import com.example.book.R
import com.example.book.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.main_toolbar.*


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)






    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)











        binding.boardTab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_boardFragment)
        }


        binding.BookBtn.setOnClickListener() {
            it.findNavController().navigate(R.id.action_homeFragment_to_bookFragment)

        }

        binding.Test1.setOnClickListener() {
            it.findNavController().navigate(R.id.action_homeFragment_to_test1Fragment)
        }

        binding.Test2.setOnClickListener() {
            it.findNavController().navigate(R.id.action_homeFragment_to_test2Fragment)

        }
        binding.homeTab.setOnClickListener(){






        }



        return binding.root
    }







}


