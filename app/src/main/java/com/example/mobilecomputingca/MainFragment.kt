package com.example.mobilecomputingca

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilecomputingca.databinding.MainFragmentBinding

class MainFragment : Fragment() {


    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

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

        return binding.root

    }



}