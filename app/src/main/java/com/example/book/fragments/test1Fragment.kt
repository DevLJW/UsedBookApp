package com.example.book.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.book.R
import com.example.book.databinding.FragmentHomeBinding
import com.example.book.databinding.FragmentTest1Binding


class test1Fragment : Fragment() {
    private lateinit var binding: FragmentTest1Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test1, container, false)



        binding.homeTab.setOnClickListener() {
            it.findNavController().navigate(R.id.action_test1Fragment_to_homeFragment)
        }

        binding.boardTab.setOnClickListener {
            it.findNavController().navigate(R.id.action_test1Fragment_to_boardFragment)
        }


        binding.BookBtn.setOnClickListener() {
            it.findNavController().navigate(R.id.action_test1Fragment_to_bookFragment)

        }



        binding.Test2.setOnClickListener() {
            it.findNavController().navigate(R.id.action_test1Fragment_to_test2Fragment)

        }


        return binding.root
    }


}