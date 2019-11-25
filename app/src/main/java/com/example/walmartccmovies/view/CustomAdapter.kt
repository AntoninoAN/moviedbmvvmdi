package com.example.walmartccmovies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartccmovies.R
import com.example.walmartccmovies.model.MovieItem
import com.example.walmartccmovies.model.MovieItemDetail
import com.example.walmartccmovies.model.MovieResponse
import com.squareup.picasso.Picasso

class CustomAdapter(val listener: OpenMovieDetail): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    var dataSet: MovieResponse?= null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
        CustomViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,
            false))

    override fun getItemCount(): Int = dataSet?.results?.size ?: 0

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        dataSet?.results.let {
            holder.onBindData(it!![position], listener)
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val moviePoster: ImageView = itemView.findViewById(R.id.iv_item_movie)
        val movieTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val moviePopularity: TextView = itemView.findViewById(R.id.tv_item_popularity)

//        fun bindData()
//            moviePoster.setOnClickListener {  }
//        }
    }

    fun CustomViewHolder.onBindData(data: MovieItem, listener: OpenMovieDetail){
        this.moviePopularity.text = data.popularity.toString()
        this.movieTitle.text = data.original_title
        this.moviePoster.setOnClickListener { listener.openMovieDetail(data.id) }
        Picasso.get().load(data.poster_path).into(this.moviePoster)
    }
}