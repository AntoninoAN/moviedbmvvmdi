package com.example.walmartccmovies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse (var page: Int,
                          var total_pages: Int,
                          var results: ArrayList<MovieItem>):Parcelable

@Parcelize
data class MovieItem (var popularity: Int,
                      var id: Int,
                      var poster_path: String,
                      var original_title: String,
                      var title: String,
                      var overview: ArrayList<String>,
                      var release_date: String): Parcelable
@Parcelize
data class MovieItemDetail (var backdrop_path: String,
                            var original_title: String,
                            var overview: String,
                            var popularity: Number,
                            var tagline: String): Parcelable