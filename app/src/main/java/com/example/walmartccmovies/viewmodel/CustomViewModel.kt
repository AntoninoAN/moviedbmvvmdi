package com.example.walmartccmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walmartccmovies.model.MovieItemDetail
import com.example.walmartccmovies.model.MovieResponse
import com.example.walmartccmovies.model.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomViewModel(val network: Network) : ViewModel() {
    //todo update popular movies
    //todo get movie item
    private  var dataSetPopularMovieList: MutableLiveData<MovieResponse> = MutableLiveData()
    private  var errorObservable: MutableLiveData<String> = MutableLiveData()
    private  var dataSetMovieDetail: MutableLiveData<MovieItemDetail> = MutableLiveData()

    fun getPopularItems() = dataSetPopularMovieList
    fun getMovieItem() = dataSetMovieDetail
    fun getErrorMessage() = errorObservable

    fun initNetworkPopularMovie() {
        network.apiResponse.getPopularMovies(api_Key = "f03122892c28dca0f58546edff017561").enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                errorObservable.value = "Ups! something bad happend because:\n $t.message"
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                dataSetPopularMovieList.value = response.body()
            }
        })
    }

    fun nextPageNetworkPopularMovie(page: String) {
        network.apiResponse.getPopularMovies(page, "f03122892c28dca0f58546edff017561").enqueue(object :
            Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                errorObservable.value = "Ups! something bad happend because:\n $t.message"
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                dataSetPopularMovieList.value = response.body()
            }
        })
    }

    fun initNetworkDetailMovie(movieId: Int) {
        network.apiResponse.getMovieDetail(movieId, "f03122892c28dca0f58546edff017561").enqueue(object :
            Callback<MovieItemDetail> {
            override fun onFailure(call: Call<MovieItemDetail>, t: Throwable) {
                errorObservable.value = "Ups! something bad happend because:\n $t.message"
            }

            override fun onResponse(call: Call<MovieItemDetail>, response: Response<MovieItemDetail>) {
                dataSetMovieDetail.value= response.body()
            }
        })
    }
}