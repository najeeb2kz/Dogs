package com.chaudhry.najeeb.dogs.viewmodel

import com.chaudhry.najeeb.dogs.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chaudhry.najeeb.dogs.data.DogEntity
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class DogViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {
    val dogsFromDb: LiveData<List<DogEntity>> = repository.dogsFromDb

    private val _status = MutableLiveData<String>() //Display status on screen
    val status: LiveData<String> = _status

    private val _dogs = MutableLiveData<List<DogEntity>>()
    val dogs: LiveData<List<DogEntity>> = _dogs

    fun fetchDogsFromApi() {
        Log.d(TAG, "fetchDogsFromApi() called")
        viewModelScope.launch {
            val dogs = repository.fetchDogsFromApi()
            if (dogs.isEmpty()) {
                _status.value = "No dogs fetched from API"
            } else {
                _status.value = "Fetched from API"
                _dogs.value = dogs
            }
        }
    }

    fun fetchDogsFromDb() {
        Log.d(TAG, "fetchDogsFromDb() called")
        viewModelScope.launch {
            repository.dogsFromDb.observeForever { dogs ->
                Log.d(TAG, "Dogs from DB: $dogs")
                if (dogs.isNullOrEmpty()) {
                    _status.value = "DB is empty"
                } else {
                    _status.value = "Fetched from DB"
                    _dogs.value = dogs
                }
            }
        }
    }

    fun resetDb() {
        Log.d(TAG, "resetDb() called")
        viewModelScope.launch {
            repository.clearDB()
            _status.value = "DB cleared"
        }
    }

    //Clear lazyColumn on screen
    fun clearList() {
        _dogs.value = emptyList()
        _status.value = "List cleared"
    }

    companion object {
        private const val TAG = "DogViewModel"
    }
}