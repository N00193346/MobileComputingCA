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
import com.example.mobilecomputingca.databinding.PopularFragmentBinding

class PopularFragment : Fragment(),
    FilmsListAdapter.ListItemListener {

    private lateinit var viewModel: PopularViewModel
    private lateinit var binding: PopularFragmentBinding
    private lateinit var adapter: FilmsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Remove back icon from appearing
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding = PopularFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PopularViewModel::class.java)

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
            Log.i("I'm on the popular page", "test")
            adapter = FilmsListAdapter(requireContext(), it, this@PopularFragment)
            binding.recyclerView.adapter = adapter
            //Telling the recycler view is going to be a list
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        })


        return binding.root

    }

    override fun onItemClick(filmId: Int, filmTitle: String, filmDescription: String, filmReleaseDate: String, filmPoster: String) {
        Log.i(TAG, "onItemClick: received film id $filmId")

        val action = PopularFragmentDirections.actionPopularFragmentToEditorFragment(filmId, filmTitle, filmDescription, filmPoster)
        findNavController().navigate(action)
    }


}