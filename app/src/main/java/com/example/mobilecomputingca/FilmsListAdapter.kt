package com.example.mobilecomputingca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobilecomputingca.databinding.ListItemBinding
import com.example.mobilecomputingca.model.Film

//Film data must first be passed into the adapter
class FilmsListAdapter(val context: Context,
                       private val filmsList: List<Film>,
                       private val listener: ListItemListener) :

        RecyclerView.Adapter<FilmsListAdapter.ViewHolder>(){

        inner class ViewHolder(itemView: View):
                RecyclerView.ViewHolder(itemView){
                val binding = ListItemBinding.bind(itemView)
            }

    //Get reference to views and return viewholder object
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //Get reference to the route of the xml file
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    //Called each time data is passed to a recycler view row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Film's position will be the index
        val film = filmsList[position]
        with(holder.binding) {
            //Applying the data to the film title item
            filmTitle.text = film.title + " (" + film.release_date.substring(0,4) + ")"
            //Applying the data to the film image item
            Glide.with(context)
                .load(POSTERURL + film.poster_path)
                .into(filmPosterImage)
            //When this item is pressed
            root.setOnClickListener{
                //When the item is clicked, pass in the film details
                listener.onItemClick(film.id, film.title, film.overview, film.release_date, film.poster_path)
            }
        }
    }

    //Find out how many films are in the list
    override fun getItemCount() = filmsList.size

    //Listener so item knows when it's been clicked
    interface ListItemListener {
        //Pass these items through
        fun onItemClick(filmId: Int, filmTitle: String, filmDescription: String, filmReleaseDate: String, filmPoster: String)
    }
}