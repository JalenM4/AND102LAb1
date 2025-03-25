package com.codepath.bitfitp2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LogFragment : Fragment() {

    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var addButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // 🔍 Find views
        foodRecyclerView = view.findViewById(R.id.rvFoodList)
        addButton = view.findViewById(R.id.btnAddFood)

        // 🔁 Set layout manager (adapter setup comes later)
        foodRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        // foodRecyclerView.adapter = YourAdapterHere()

        CoroutineScope(Dispatchers.IO).launch {
            val foods = (requireActivity().application as BitFitApplication).db.foodDao().getAll()
            withContext(Dispatchers.Main) {
                foodRecyclerView.adapter = FoodAdapter(foods)
            }
        }

        // ➕ Set up Add button
        addButton.setOnClickListener {
            val intent = Intent(requireContext(), AddFoodActivity::class.java)
            startActivity(intent)
        }
    }
}
