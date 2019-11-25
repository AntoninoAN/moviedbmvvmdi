package com.example.walmartccmovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartccmovies.R
import com.example.walmartccmovies.model.MovieResponse

class PopularMovieList : Fragment(){
    private lateinit var adapter: CustomAdapter
    private lateinit var recyclerView: RecyclerView

    companion object{
        const val KEY_DATA_ITEM = "popularMovieList_popular_data"
        private lateinit var listener: OpenMovieDetail
        fun newInstance(data: MovieResponse,
                        listener: OpenMovieDetail): PopularMovieList{
            val instance = PopularMovieList()
            val bundle = Bundle()
            bundle.putParcelable(KEY_DATA_ITEM, data)
            instance.arguments = bundle
            this.listener = listener
            return instance
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val view= inflater.inflate(
            R.layout.fragment_popular_movie,
            container,
            false)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        adapter = CustomAdapter(listener)
        recyclerView.adapter = adapter
        return view
    }

    fun bindData(){
        arguments?.let {
            adapter.dataSet = it.getParcelable(KEY_DATA_ITEM)
        }
    }
}