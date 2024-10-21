package com.chaudhry.najeeb.dogs.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDao {
    @Query("SELECT * FROM dogs")
    fun getAllDogs(): LiveData<List<DogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogs: List<DogEntity>)

    @Query("DELETE FROM dogs")
    suspend fun deleteAll()
}