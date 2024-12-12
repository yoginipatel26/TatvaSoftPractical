package com.example.yoginipatelpractical.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.yoginipatelpractical.models.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user : Item)

    @Update
    suspend fun updateUser(user : Item)

    @Delete
    suspend fun deleteUser(user : Item)

    @Query("SELECT * FROM Item")
    fun getAllUSersFromDB() : LiveData<List<Item>>


}