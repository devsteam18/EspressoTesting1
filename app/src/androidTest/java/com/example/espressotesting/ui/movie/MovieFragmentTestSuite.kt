package com.example.espressotesting.ui.movie

import com.codingwithmitch.espressouitestexamples.ui.movie.DirectorsFragmentTest
import com.codingwithmitch.espressouitestexamples.ui.movie.MovieDetailFragmentTest
import com.codingwithmitch.espressouitestexamples.ui.movie.MovieListFragmentTest
import com.codingwithmitch.espressouitestexamples.ui.movie.StarActorsFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    DirectorsFragmentTest::class,
    StarActorsFragmentTest::class,
    MovieDetailFragmentTest::class,
    MovieListFragmentTest::class
)
class MovieFragmentTestSuite