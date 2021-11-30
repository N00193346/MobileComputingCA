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
<<<<<<< HEAD

                        //listener reference
=======
>>>>>>> a343031820462751fbfb63e6233757858d5fdf99
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
            filmTitle.text = film.title + " (" + film.release_date.substring(0,4) + ")"
<<<<<<< HEAD
            // load the image from the web(imageName)
            // into the plantImage object in the layout
//            Glide.with(context)
//                .load(POSTERURL + film.poster_path)
//                .into(filmPosterImage)
=======
            Glide.with(context)
                .load(POSTERURL + film.poster_path)
                .into(filmPosterImage)
>>>>>>> a343031820462751fbfb63e6233757858d5fdf99
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
        fun onItemClick(filmId: Int, filmTitle: String, filmDescription: String, filmReleaseDate: String, filmPoster: String)
    }
}