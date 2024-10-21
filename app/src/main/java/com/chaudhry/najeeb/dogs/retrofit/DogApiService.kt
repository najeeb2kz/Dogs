package com.chaudhry.najeeb.dogs.retrofit

import retrofit2.http.GET

interface DogApiService {
//    @GET("images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=1")
//    suspend fun getDogs(): Response<ResponseBody>
//   suspend fun getDogs(): List<ApiResponse>

    @GET("images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=10&api_key=live_1bLbtgEl0zORsl8ZMS37nzvk7pOC0Sx24aR7KuntZCqGLaqAxdF007dyp36wJtns")
    suspend fun getDogs(): List<ApiResponse>


}