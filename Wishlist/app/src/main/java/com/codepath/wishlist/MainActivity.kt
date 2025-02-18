package com.codepath.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var items: MutableList<WishlistItem>
    lateinit var nameEntry: EditText
    lateinit var urlEntry: EditText
    lateinit var priceEntry: EditText
    lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = mutableListOf()

        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)
        val wishlistAdapter = WishlistAdapter(items)
        wishlistRv.adapter = wishlistAdapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        nameEntry = findViewById<EditText>(R.id.nameEntry)
        urlEntry = findViewById<EditText>(R.id.urlEntry)
        priceEntry = findViewById<EditText>(R.id.priceEntry)
        submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            var newItem : WishlistItem = WishlistItem(
                nameEntry.text.toString(),
                urlEntry.text.toString(),
                priceEntry.text.toString().toDouble()
            )
            items.add(newItem)
            wishlistAdapter.notifyDataSetChanged()
            // Clear out text
            nameEntry.text.clear()
            urlEntry.text.clear()
            priceEntry.text.clear()
        }
    }
}