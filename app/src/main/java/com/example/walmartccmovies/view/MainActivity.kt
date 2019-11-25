
package com.example.walmartccmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.walmartccmovies.R
import com.example.walmartccmovies.di.modules.NetworkProvider
import com.example.walmartccmovies.di.modules.ViewModelFactoryProvider
import com.example.walmartccmovies.model.MovieItemDetail
import com.example.walmartccmovies.model.MovieResponse
import com.example.walmartccmovies.model.Network
import com.example.walmartccmovies.viewmodel.CustomViewModel
import com.example.walmartccmovies.viewmodel.CustomViewModelFactory

class MainActivity : AppCompatActivity(), OpenMovieDetail {

    val network: Network by lazy { NetworkProvider().provideNetwor() }
    val vmFactory: CustomViewModelFactory by lazy { ViewModelFactoryProvider()
        .providerViewModelFactory(network) }

    override fun openMovieDetail(movieID: Int) {
        viewModel.initNetworkDetailMovie(movieID)
    }

    val viewModel: CustomViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(CustomViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getPopularItems().observe(
            this,
            Observer {
                val fragmentMovieList = PopularMovieList.newInstance(it, this)
                initPopularList(fragmentMovieList)
            })
        viewModel.getMovieItem().observe(
            this,
            Observer { initMovieDetailView(it) }
        )
        viewModel.getErrorMessage().observe(
            this,
            Observer { errorMessage(it) }
        )

        viewModel.initNetworkPopularMovie()
    }

    fun initPopularList(fragmentMovieList: PopularMovieList){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayout,
                fragmentMovieList)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun initMovieDetailView(data: MovieItemDetail){
        val fragment = MovieDetailView.newInstance(data)
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content,
                fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    fun errorMessage(error: String) = Toast.makeText(this, error, Toast.LENGTH_LONG).show()
}
