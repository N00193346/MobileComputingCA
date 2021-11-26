package com.example.mobilecomputingca

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.mobilecomputingca.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {


    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Remove back icon from appearing
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding = HomeFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        //If the popular button is pressed, go to popular page
        binding.popularButton.setOnClickListener {
            goPopular()
        }

        return binding.root
    }

    fun goPopular() {
        val action = HomeFragmentDirections.actionHomeFragmentToPopularFragment()
        findNavController().navigate(action)
    }
}
