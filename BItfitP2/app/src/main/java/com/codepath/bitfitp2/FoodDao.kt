package com.codepath.bitfitp2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {

    @Query("SELECT * FROM food_table")
    fun getAll(): List<FoodEntity>

    @Insert
    fun insert(vararg foods: FoodEntity)

    @Query("DELETE FROM food_table")
    fun deleteAll()
}