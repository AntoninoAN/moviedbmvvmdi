package com.example.walmartccmovies.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI{
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") page: String = "1",
        @Query("api_key") api_Key: String): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") api_Key: String): Call<MovieItemDetail>
}