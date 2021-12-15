package com.example.espressotesting.data.source

import com.example.espressotesting.data.Movie


interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}