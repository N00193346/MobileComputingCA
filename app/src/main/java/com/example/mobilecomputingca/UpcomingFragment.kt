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
import com.bumptech.glide.Glide

import com.example.mobilecomputingca.databinding.UpcomingFragmentBinding


class UpcomingFragment : Fragment(),
    FilmsListAdapter.ListItemListener {

    private lateinit var viewModel: UpcomingViewModel
    private lateinit var binding: UpcomingFragmentBinding
    private lateinit var adapter: FilmsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Remove back icon from appearing
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding = UpcomingFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(UpcomingViewModel::class.java)

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
//            Log.i("filmLogging", it.toString())
            Log.i("I'm on the latest page", "Test")
            adapter = FilmsListAdapter(it, this@UpcomingFragment)


            binding.recyclerView.adapter = adapter
            //Telling the recycler view is going to be a list
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)


        })


        return binding.root

    }

    override fun onItemClick(filmId: Int, filmTitle: String, filmDescription: String, filmReleaseDate: String) {
        Log.i(TAG, "onItemClick: received film id $filmId")
        //Sending id from main fragment to the editor fragment
        val action = UpcomingFragmentDirections.actionUpcomingFragmentToEditorFragment(filmId, filmTitle, filmDescription)
        findNavController().navigate(action)
    }


}