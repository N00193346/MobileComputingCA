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

        //Search query is equal to text in the search view
        val searchQuery = binding.searchView;
        //Text listner to detect changes within the searchview
        searchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(userSearch: String?): Boolean {
                //If the user presses search, navigate to the results page with user query
                val action = SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(userSearch.toString())
                findNavController().navigate(action)
                return false
            }

            //Function to output text in searchview when text changed
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("Search text:", "$newText")
                return false
            }

        })
        //If the search button is pressed
        binding.searchButton.setOnClickListener {
            //Get the text from the search view, pass to searhQuery function
            searchQuery(binding.searchView.getQuery().toString())
        }

        return binding.root


    }

    //Function to pass the text from the search view to the search result fragment
    fun searchQuery(searchQuery: String) {
        Log.i(TAG, " search Query $searchQuery")
        //Action dictates which fragment to send the variables to
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
