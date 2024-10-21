package com.chaudhry.najeeb.dogs.di

import android.content.Context
import androidx.room.Room
import com.chaudhry.najeeb.dogs.retrofit.DogApiService
import com.chaudhry.najeeb.dogs.data.DogDao
import com.chaudhry.najeeb.dogs.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDogApiService(): DogApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "dog_database"
        ).build()
    }

    @Provides
    fun provideDogDao(database: AppDatabase): DogDao {
        return database.dogDao()
    }
}