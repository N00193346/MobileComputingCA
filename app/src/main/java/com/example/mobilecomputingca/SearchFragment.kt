package com.example.mobilecomputingca

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.mobilecomputingca.databinding.HomeFragmentBinding
import com.example.mobilecomputingca.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Remove back icon from appearing
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding = SearchFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)


        val searchQuery = binding.searchView;
        searchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val action = SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(p0.toString())
                findNavController().navigate(action)
                return false
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("Search text:", "$newText")
                return false
            }
        })


        binding.searchButton.setOnClickListener {
            searchQuery(searchQuery as String)
        }

        return binding.root


    }

    fun searchQuery(searchQuery: String) {
        Log.i(TAG, " search Query $searchQuery")

        val action = SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(searchQuery)
        findNavController().navigate(action)
    }

}
