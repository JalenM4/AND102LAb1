package com.codepath.bitfitp1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.bitfitp1.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {
    private val articles = mutableListOf<DisplayFood>()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var addFoodButtonView: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ListActivity", "launching")

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        foodRecyclerView = findViewById(R.id.foods)
        val bitfitAdapter = BitFitAdapter(this, articles)
        foodRecyclerView.adapter = bitfitAdapter
        foodRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        // Using Flow, update the RecyclerView whenever the db is updated
        // Taken from the provided Lab 5 code
        lifecycleScope.launch {
            (application as BitFitApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.name,
                        entity.calories
                    )
                }.also { mappedList ->
                    articles.clear()
                    articles.addAll(mappedList)
                    bitfitAdapter.notifyDataSetChanged()
                }
            }
        }

        // Need to check if this ListActivity has an extra
        // If so -- that's a new food to add passed from EntryActivity!
        val food = intent.getSerializableExtra("ENTRY_EXTRA")
        // First, check if the EXTRA exists:
        if (food != null) {
            Log.d("ListActivity", "got an extra")
            Log.d("ListActivity", (food as DisplayFood).toString())

            lifecycleScope.launch(IO) {
                (application as BitFitApplication).db.foodDao().insert(
                    FoodEntity(
                        name = food.name ?: "Unknown",
                        calories = food.calories
                    )
                )
            }
        }
        else {
            // No extra, so we don't need to do anything.
            Log.d("ListActivity", "no extra")
        }

        // If the "Add Food" button is clicked, swap to EntryActivity
        addFoodButtonView = findViewById(R.id.button)
        addFoodButtonView.setOnClickListener {
            Log.d("ListActivity", "add new food clicked")
            val intent = Intent(this, EntryActivity::class.java)
            this.startActivity(intent)
        }
    }
}