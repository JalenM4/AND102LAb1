package com.codepath.bitfitp2

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.codepath.bitfitp2.databinding.FragmentDashboardBinding
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val foodList = withContext(Dispatchers.IO) {
                (requireActivity().application as BitFitApplication).db.foodDao().getAll()
            }

            val totalCalories = foodList.sumOf { it.calories }
            val averageCalories = if (foodList.isNotEmpty()) totalCalories / foodList.size else 0

            binding.totalCaloriesText.text = "Total Calories: $totalCalories"
            binding.avgCaloriesText.text = "Average per Meal: $averageCalories"

            setupChart(foodList)
        }
    }

    private fun setupChart(foodList: List<FoodEntity>) {
        val entries = foodList.mapIndexed { index, food ->
            Entry(index.toFloat(), food.calories.toFloat())
        }

        val dataSet = LineDataSet(entries, "Calories Logged").apply {
            color = Color.MAGENTA
            valueTextColor = Color.BLACK
            lineWidth = 2f
            circleRadius = 4f
            setCircleColor(Color.MAGENTA)
            setDrawValues(true)
        }

        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData

        binding.lineChart.apply {
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            axisRight.isEnabled = false
            axisLeft.axisMinimum = 0f
            description.text = "Meal #"
            setTouchEnabled(true)
            animateX(500)
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}