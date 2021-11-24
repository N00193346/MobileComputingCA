package com.example.mobilecomputingca

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilecomputingca.databinding.MainFragmentBinding
import com.example.mobilecomputingca.model.Film

class MainFragment : Fragment(),
    FilmsListAdapter.ListItemListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: FilmsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Remove back icon from appearing
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)

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

        //Display the data to the user
        viewModel.films.observe(viewLifecycleOwner, Observer {
            //it refers to the films objects being received from the films list
            Log.i("filmLogging", it.toString())
            adapter = FilmsListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            //Telling the recycler view is going to be a list
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        })

//        //Button to fetch posts
//        binding.button.setOnClickListener {
//            viewModel.getPosts()
//        }

        return binding.root

    }

    override fun onItemClick(filmId: Int, filmTitle: String, filmDescription: String) {
        Log.i(TAG, "onItemClick: received film id $filmId")
        //Sending id from main fragment to the editor fragment
        val action = MainFragmentDirections.actionEditFilm(filmId, filmTitle, filmDescription)
        findNavController().navigate(action)
    }


}