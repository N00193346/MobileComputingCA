package com.example.mobilecomputingca

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilecomputingca.data.FilmEntity
import com.example.mobilecomputingca.databinding.ListItemBinding

//Film data must first be passed into the adapter
class FilmsListAdapter(private val filmsList: List<FilmEntity>) :
        RecyclerView.Adapter<FilmsListAdapter.ViewHolder>(){


            inner class ViewHolder(itemView: View):
                RecyclerView.ViewHolder(itemView){
                val binding = ListItemBinding.bind(itemView)
            }
        }