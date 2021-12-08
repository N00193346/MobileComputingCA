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
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
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
        binding.filmDate.setText("${args.filmReleaseDate.substring(8,10)}/${args.filmReleaseDate.substring(5,7)}/${args.filmReleaseDate.substring(0,4)}")
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
        //Check to see if the film is in the watchlist
        viewModel.getFavourite(args.filmId)

        //Check if the film is in the database and assign text to button
        viewModel.currentFavourite.observe(viewLifecycleOwner, Observer {
            with (it) {
                if (it != null) {
                    currentFavourite = it
                }
                if (viewModel.currentFavourite.value == null){
                    binding.favouriteButton.text = "Add to Watchlist"
                } else {
                    binding.favouriteButton.text = "Remove from Watchlist"
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


        //When do you user presses button
        binding.favouriteButton.setOnClickListener {
            //add to watch list
            saveFavourite()
            //Change text displayed on the button
            changeButtonText()

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

    //Save film into the watchlist
    private fun saveFavourite() {
        //If there is no current favourite, the film does not exist in the watch list
        if(viewModel.currentFavourite.value == null) {
            Log.i("WatchList", "Adding film to watchlist")
            //Insert film into the watchlist
            viewModel.saveFavourite(
                Favourite(args.filmId, args.filmTitle, args.filmDescription, args.filmPoster, args.filmReleaseDate)
            )
            //Display Added film pop up
            displayAddedDialog()
            viewModel.getFavourite(args.filmId)
        }
            //Else remove the film from the watch list
            else  {
           Log.i("WatchList", "Film already in watchlist")
            viewModel.removeFavourite(args.filmId)
            Log.i("WatchList", "Film removed from watchlist")
            displayRemovedDialog()
            //Set the value of favourite to null so we know the film is no longer in the watchlist
            viewModel.nullFavourite()
            }
    }

    //Change the text on the button depending on if the film is already in the watch list
    private fun changeButtonText() {
        if (viewModel.currentFavourite.value == null){
            binding.favouriteButton.text = "Add to Watchlist"
        } else {
            binding.favouriteButton.text = "Remove from Watchlist"
        }
    }

    //Change Display Alert dialog if film added

    private fun displayAddedDialog() {
        val addedFilmDialog = AlertDialog.Builder(requireContext())
        addedFilmDialog.setTitle("Success")
        addedFilmDialog.setMessage("Film has been added to the Database")
        addedFilmDialog.setPositiveButton("Dismiss", {dialogInterface: DialogInterface, i: Int ->})
        addedFilmDialog.show()
    }

    private fun displayRemovedDialog() {
        val addedFilmDialog = AlertDialog.Builder(requireContext())
        addedFilmDialog.setTitle("Success")
        addedFilmDialog.setMessage("Film has been removed from the Database")
        addedFilmDialog.setPositiveButton("Dismiss", {dialogInterface: DialogInterface, i: Int ->})
        addedFilmDialog.show()
    }



}



