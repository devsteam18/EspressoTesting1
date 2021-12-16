package com.example.espressotesting.ui.movie

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.espressotesting.R
import com.example.espressotesting.data.Movie
import com.example.espressotesting.util.EspressoIdlingResource

class MoviesListAdapter(private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_movie_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Movie>) {
        EspressoIdlingResource.increment()
        val dataCommitCallback = Runnable {
            EspressoIdlingResource.decrement()
        }
        differ.submitList(list, dataCommitCallback)
    }

    class MovieViewHolder constructor(itemView: View, private val interaction: Interaction?) : RecyclerView.ViewHolder(itemView) {

        lateinit var movie_title:TextView
        lateinit var movie_star_actors:TextView
        lateinit var movie_image:ImageView

        fun bind(item: Movie) = with(itemView) {


            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            movie_title=findViewById(R.id.movie_title)
            movie_image=findViewById(R.id.movie_image)
            movie_star_actors=findViewById(R.id.movie_star_actors)

            movie_title.text = item.title
            Glide.with(itemView)
                .load(item.image)
                .into(movie_image)
            item.star_actors?.let {
                for(index in 0 until it.size){
                    var appendValue: String = it[index]
                    if(index < (it.size - 1)){
                        appendValue += ", "
                    }
                    movie_star_actors.append(appendValue)
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Movie)
    }
}
