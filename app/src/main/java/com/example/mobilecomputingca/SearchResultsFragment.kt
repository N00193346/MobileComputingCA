package com.example.mobilecomputingca

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilecomputingca.databinding.SearchResultsFragmentBinding


class SearchResultsFragment : Fragment(),

    FilmsListAdapter.ListItemListener {

        private lateinit var viewModel: SearchResultsViewModel
        private val args: SearchResultsFragmentArgs by navArgs()
        private lateinit var binding: SearchResultsFragmentBinding
        private lateinit var adapter: FilmsListAdapter
        private lateinit var searchQuery : String

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

            binding = SearchResultsFragmentBinding.inflate(inflater, container, false)
            viewModel = ViewModelProvider(this).get(SearchResultsViewModel::class.java)
            searchQuery = args.searchQuery
            viewModel.getSearchResults(searchQuery)

            //Setting up recyclerview properties
            with(binding.recyclerView) {
                //Fixed size so every row has same height
                setHasFixedSize(true)
            }


            //Display the data to the user
            viewModel.films.observe(viewLifecycleOwner, Observer {
                if(it.isEmpty()){
                    displayDialog()
                    saveAndReturn()
                    //else display the watch list
                } else {
//            Log.i("filmLogging", it.toString())
                    Log.i("I'm on the results page", "Test")

                    adapter = FilmsListAdapter(requireContext(), it, this@SearchResultsFragment)
                    binding.recyclerView.adapter = adapter
                    //Telling the recycler view is going to be a list
                    binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                }
            })


            return binding.root

        }

        override fun onItemClick(filmId: Int, filmTitle: String, filmDescription: String, filmReleaseDate: String, filmPoster: String) {
            Log.i(TAG, "onItemClick: received film id $filmId")
            //Sending id from main fragment to the editor fragment
            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToEditorFragment(filmId, filmTitle, filmDescription, filmPoster, filmReleaseDate)
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

    private fun displayDialog() {
        val addedFilmDialog = AlertDialog.Builder(requireContext())
        addedFilmDialog.setTitle("No Film found")
        addedFilmDialog.setMessage("There is no film in the database related to your search query, please try again")
        addedFilmDialog.setPositiveButton("Dismiss", { dialogInterface: DialogInterface, i: Int ->})
        addedFilmDialog.show()
    }


}