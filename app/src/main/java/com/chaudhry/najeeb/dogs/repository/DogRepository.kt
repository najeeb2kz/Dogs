package com.chaudhry.najeeb.dogs.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.chaudhry.najeeb.dogs.data.DogDao
import com.chaudhry.najeeb.dogs.data.DogEntity
import com.chaudhry.najeeb.dogs.retrofit.ApiResponse
import com.chaudhry.najeeb.dogs.retrofit.DogApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor(
    private val dogApiService: DogApiService,
    private val dogDao: DogDao
) {
    val dogsFromDb: LiveData<List<DogEntity>> = dogDao.getAllDogs()

    suspend fun fetchDogsFromApi(): List<DogEntity> {
        Log.d(TAG, "DogRepository: fetchDogsFromApi()")
        val apiResponses = dogApiService.getDogs()
        Log.d(TAG, "API Responses: $apiResponses")
        val dogs = apiResponses.map { mapApiResponseToDogEntity(it) }
        dogDao.insertAll(dogs)
        return dogs
    }

    suspend fun clearDB() {
        dogDao.deleteAll()
    }

    private fun mapApiResponseToDogEntity(apiResponse: ApiResponse): DogEntity {
        val breed = apiResponse.breeds?.firstOrNull()
        return DogEntity(
            id = apiResponse.id?.toString() ?: "",
            name = breed?.name ?: "Unknown",
            bredFor = breed?.bred_for ?: "Unknown",
            breedGroup = breed?.breed_group ?: "Unknown",
            lifeSpan = breed?.life_span ?: "Unknown",
            temperament = breed?.temperament ?: "Unknown",
            url = apiResponse.url ?: ""
        )
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}