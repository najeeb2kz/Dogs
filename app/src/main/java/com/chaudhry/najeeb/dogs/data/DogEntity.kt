package com.chaudhry.najeeb.dogs.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//This class represents the database entity, which is used by Room to map the data to a
// SQLite table. It is specifically designed for database operations.
//This class used for database operations, such as inserting and querying data from the database.
// This is the class that maps the API response to the database entity.
@Entity(tableName = "dogs")
data class DogEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val bredFor: String?,
    val breedGroup: String?,
    val lifeSpan: String?,
    val temperament: String?,
    val url: String?
)