package com.example.walmartccmovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.walmartccmovies.R
import com.example.walmartccmovies.model.MovieItemDetail

class MovieDetailView : Fragment() {

    lateinit var movieItemDetailTitle: TextView
    lateinit var movieItemDetailOverview: TextView
    lateinit var movieItemDetailPopularity: TextView
    lateinit var movieItemDetailTagLine: TextView
    lateinit var movieItemDetailPoster: ImageView

    companion object {
        const val KEY_DATA_ITEM_DETAIL = "MOVIEDETAILVIEW_DETAIL_VIEW"
        fun newInstance(data: MovieItemDetail): MovieDetailView {
            val instance = MovieDetailView()
            val bundle = Bundle()
            bundle.putParcelable(KEY_DATA_ITEM_DETAIL, data)
            instance.arguments = bundle
            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_detail_movie,
            container,
            false)
        movieItemDetailTitle = view.findViewById(R.id.tv_movie_detail_title)
        movieItemDetailOverview = view.findViewById(R.id.tv_movie_detail_overview)
        movieItemDetailPopularity = view.findViewById(R.id.tv_movie_detail_popularity)
        movieItemDetailTagLine = view.findViewById(R.id.tv_movie_detail_tagline)
        movieItemDetailPoster = view.findViewById(R.id.iv_movie_detail)
        return view
    }

    fun bindViewData() {
        arguments?.let {
            movieItemDetailTagLine.text = it.getParcelable<MovieItemDetail>(KEY_DATA_ITEM_DETAIL)!!.tagline
            movieItemDetailPopularity.text = it.getParcelable<MovieItemDetail>(KEY_DATA_ITEM_DETAIL)!!.popularity.toString()
            movieItemDetailOverview.text = it.getParcelable<MovieItemDetail>(KEY_DATA_ITEM_DETAIL)!!.overview
            movieItemDetailTitle.text = it.getParcelable<MovieItemDetail>(KEY_DATA_ITEM_DETAIL)!!.original_title
        }
    }
}