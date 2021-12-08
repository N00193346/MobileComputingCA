package com.example.mobilecomputingca

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.mobilecomputingca.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Creating back icon icon
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_back)
        }

        //Showing the options menu
        setHasOptionsMenu(true)

        binding = SearchFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)


        val searchQuery = binding.searchView;
        searchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(userSearch: String?): Boolean {
                val action = SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(userSearch.toString())
                findNavController().navigate(action)
                return false
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("Search text:", "$newText")
                return false
            }

        })

        binding.searchButton.setOnClickListener {
            searchQuery(binding.searchView.getQuery().toString())
        }

        return binding.root


    }

    fun searchQuery(searchQuery: String) {
        Log.i(TAG, " search Query $searchQuery")

        val action = SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(searchQuery)
        findNavController().navigate(action)
    }

    //When options menu selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Function used to navigate to previous page
    private fun saveAndReturn(): Boolean {
        //Return up one fragment
        findNavController().navigateUp()
        return true
    }

}
