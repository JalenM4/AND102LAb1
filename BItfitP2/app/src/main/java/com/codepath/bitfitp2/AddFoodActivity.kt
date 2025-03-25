package com.codepath.bitfitp2

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.codepath.bitfitp2.FoodEntity

class AddFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        val foodNameInput = findViewById<EditText>(R.id.foodNameEditText)
        val caloriesInput = findViewById<EditText>(R.id.caloriesEditText)
        val saveButton = findViewById<Button>(R.id.saveFoodButton)



        val dao = (application as BitFitApplication).db.foodDao()


        saveButton.setOnClickListener {
            val name = foodNameInput.text.toString().trim()

            val calories = caloriesInput.text.toString().toIntOrNull()
            if (calories == null) {
                Toast.makeText(this, "Calories must be a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val foodEntity = FoodEntity(name = name, calories = calories)

            if (name.isNotEmpty() && calories != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    (application as BitFitApplication).db.foodDao()
                        .insert(FoodEntity(name = name, calories = calories))
                    runOnUiThread {
                        Toast.makeText(this@AddFoodActivity, "Food added!", Toast.LENGTH_SHORT).show()
                        setResult(Activity.RESULT_OK)
                        dao.insert(foodEntity)
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}