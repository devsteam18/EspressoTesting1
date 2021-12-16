package com.example.espressotesting.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.request.RequestOptions
import com.codingwithmitch.espressouitestexamples.ui.movie.MovieListFragment
import com.example.espressotesting.R
import com.example.espressotesting.data.source.MoviesDataSource
import com.example.espressotesting.data.source.MoviesRemoteDataSource

import com.example.espressotesting.factory.MovieFragmentFactory
import com.example.espressotesting.ui.UICommunicationListener

class MainActivity : AppCompatActivity(),
    UICommunicationListener
{

    private lateinit var  progress_bar:ProgressBar


    // dependencies (typically would be injected with dagger)
    lateinit var requestOptions: RequestOptions
    lateinit var moviesDataSource: MoviesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        supportFragmentManager.fragmentFactory = MovieFragmentFactory(
            requestOptions,
            moviesDataSource
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       progress_bar=findViewById(R.id.progress_bar)

        init()
    }

    override fun loading(isLoading: Boolean) {
        if (isLoading)
            progress_bar.visibility = View.VISIBLE
        else
            progress_bar.visibility = View.INVISIBLE
    }

    private fun init(){
        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment::class.java, null)
                .commit()
        }
    }

    private fun initDependencies(){
        if(!::requestOptions.isInitialized){
            // glide
            requestOptions = RequestOptions
                .placeholderOf(R.drawable.default_image)
                .error(R.drawable.default_image)
        }
        if(!::moviesDataSource.isInitialized){
            // Data Source
            moviesDataSource = MoviesRemoteDataSource()
        }
    }



}