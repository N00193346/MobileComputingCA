package com.example.mobilecomputingca

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilecomputingca.databinding.MainFragmentBinding

class MainFragment : Fragment(),
    FilmsListAdapter.ListItemListener {



    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: FilmsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Setting up recyclerview properties
        with(binding.recyclerView) {
            //Fixed size so every row has same height
            setHasFixedSize(true)
            //Creating divider to put between rows
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            //Applying the divider
            addItemDecoration(divider)
        }

        //Display the sample data to the user
        viewModel.filmsList.observe(viewLifecycleOwner, Observer {
            //it refers to the films objects being received from the films list
            Log.i("filmLogging", it.toString())
            adapter = FilmsListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            //Telling the recycler view is going to be a list
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        })

        return binding.root

    }

    override fun onItemClick(filmId: Int) {
        Log.i(TAG, "onItemClick: received film id $filmId")
        //Sending id from main fragment to the editor fragment
        val action = MainFragmentDirections.actionEditFilm(filmId)
        findNavController().navigate(action)
    }


}