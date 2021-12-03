package com.example.mobilecomputingca

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mobilecomputingca.databinding.EditorFragmentBinding
import com.example.mobilecomputingca.model.Favourite


class EditorFragment : Fragment() {


    private val args: EditorFragmentArgs by navArgs()
    private lateinit var viewModel: EditorViewModel
    private lateinit var binding: EditorFragmentBinding
    private lateinit var currentFavourite: Favourite


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

        //Assign values from api to elements in the fragment
        binding = EditorFragmentBinding.inflate(inflater, container, false)
        //Setting film title
        binding.filmTitle.setText("${args.filmTitle}")
        //Setting film description
        binding.filmDescription.setText("${args.filmDescription}")
        //Setting film date
        binding.filmDate.setText("${args.filmReleaseDate}")
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
        //Check to see if the film is in the watchlist
        viewModel.getFavourite(args.filmId)

        viewModel.currentFavourite.observe(viewLifecycleOwner, Observer {
            with (it) {
                if (it != null) {
                    currentFavourite = it
                }
                Log.i("Testlist", currentFavourite.title)
            }
        })

        //Using glide to display movie poster
        Glide.with(this)
            .load(POSTERURL + args.filmPoster)
            .into(binding.filmPosterImage)

        //If the user presses the device back button, use the same back method as the icon
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )


        //When do you user presses button, add to watch list
        binding.favouriteButton.setOnClickListener {

            saveFavourite()

        }

        return binding.root
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

    private fun saveFavourite() {

        if(viewModel.currentFavourite.value == null) {
            Log.i("WatchList", "Adding film to watchlist")

            viewModel.saveFavourite(
                Favourite(args.filmId, args.filmTitle, args.filmDescription, args.filmPoster, args.filmReleaseDate)
            )
                    viewModel.getFavourite(args.filmId)
        }
            else  {
           Log.i("WatchList", "Film already in watchlist")
            viewModel.removeFavourite(args.filmId)
            Log.i("WatchList", "Film removed from watchlist")
            }
            viewModel.nullFavourite()

    }



    }

