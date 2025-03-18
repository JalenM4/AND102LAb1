package com.codepath.flixstermovie2_p2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ActorDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_detail)

        val actorName = intent.getStringExtra("actor_name") ?: "Unknown"
        val actorImage = intent.getStringExtra("actor_image")
        val actorKnownFor = intent.getStringExtra("actor_known_for") ?: "Not available"
        val actorBirthDate = intent.getStringExtra("actor_birth_date") ?: "Not available"

        val nameTextView: TextView = findViewById(R.id.actorNameDetail)
        val knownForTextView: TextView = findViewById(R.id.actorDetailKnownFor)
        val birthDateTextView: TextView = findViewById(R.id.actorDetailBirthDate)
        val imageView: ImageView = findViewById(R.id.actorDetailImage)

        nameTextView.text = actorName
        knownForTextView.text = "Best Known For: $actorKnownFor"
        birthDateTextView.text = "Birthdate: $actorBirthDate"

        if (actorImage != null) {
            val imageUrl = "https://image.tmdb.org/t/p/w500$actorImage"
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.error_placeholder)  // Use your placeholder
                .error(R.drawable.error_placeholder)
                .into(imageView)
        } else {
            imageView.setImageResource(R.drawable.error_placeholder)
        }
    }
}