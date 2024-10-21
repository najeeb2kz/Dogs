//API call is made to this url: https://api.thedogapi.com/v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&order=RANDOM&page=0&limit=10
package com.chaudhry.najeeb.dogs.retrofit

data class ApiResponse(
    val breeds: List<Breed>?,
    val id: String?,
    val url: String?,
    val width: Int?,
    val height: Int?
)

data class Breed(
    val weight: Weight?,
    val height: Height?,
    val id: Int?,
    val name: String?,
    val bred_for: String?,
    val breed_group: String?,
    val life_span: String?,
    val temperament: String?,
    val origin: String?,
    val reference_image_id: String?
)

data class Weight(
    val imperial: String?,
    val metric: String?
)

data class Height(
    val imperial: String?,
    val metric: String?
)