package com.codepath.flixstermovie2_p2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.content.Intent

class ActorAdapter(
    private val context: Context,
    private val items: List<Actor>,
    private val onItemClick: (Actor) -> Unit
) : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        // Call the click listener when an item is clicked
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.actorName)
        private val profileImageView: ImageView = itemView.findViewById(R.id.actorImage)

        fun bind(item: Actor) {
            nameTextView.text = item.name

            val imageUrl = if (item.profilePath != null) {
                "https://image.tmdb.org/t/p/w500${item.profilePath}"
            } else {
                null
            }

            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.error_placeholder)
                .error(R.drawable.error_placeholder)  
                .into(profileImageView)
        }
    }
}