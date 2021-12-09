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

        //Applying the fragment to the binding
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        //Applying the view model
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        //Assign click listeners to each button to go to different fragments
        //Search
        binding.searchButton.setOnClickListener {
            goSearch()
        }

        //Popular
        binding.popularButton.setOnClickListener {
            goPopular()
        }

        //Latest
        binding.latestButton.setOnClickListener {
            goLatest()
        }

        //WatchList
        binding.watchListButton.setOnClickListener {
            goWatchList()
        }

        return binding.root
    }

    //Functions for buttons on home screen
    //Actions used to navigate to each page
    fun goSearch() {
        val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
        findNavController().navigate(action)
    }

    fun goPopular() {
        val action = HomeFragmentDirections.actionHomeFragmentToPopularFragment()
        findNavController().navigate(action)

    }

    fun goLatest() {
        val action = HomeFragmentDirections.actionHomeFragmentToUpcomingFragment()
        findNavController().navigate(action)
    }

    fun goWatchList() {
        val action = HomeFragmentDirections.actionHomeFragmentToWatchListFragment()
        findNavController().navigate(action)
    }
}
