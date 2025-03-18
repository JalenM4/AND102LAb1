package com.codepath.flixstermovie2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NowPlayingRecyclerViewAdapter(
    private val movies:  List<Movie>,
    private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<NowPlayingRecyclerViewAdapter.MovieViewHolder>() {

        inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            var mItem: Movie? = null
            val mMovieTitle: TextView = mView.findViewById<View>(R.id.titleView) as TextView
            val mMovieDescription: TextView = mView.findViewById<View>(R.id.descriptionView) as TextView
            val mMoviePoster: ImageView = mView.findViewById<View>(R.id.posterView) as ImageView

            override fun toString(): String {
                return mMovieTitle.toString()
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_movie, parent, false)
            return MovieViewHolder(view)
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val movie = movies[position]

            holder.mItem = movie
            holder.mMovieTitle.text = movie.title
            holder.mMovieDescription.text = movie.description

            Glide.with(holder.mView)
                .load("https://image.tmdb.org/t/p/w500/" + movie.posterURL)
                .placeholder(R.drawable.ic_loading)
                .centerInside()
                .into(holder.mMoviePoster)

            holder.mView.setOnClickListener {
                holder.mItem?.let { book ->
                    mListener?.onItemClick(book)
                }
            }
        }

        override fun getItemCount(): Int {
            return movies.size
        }
}