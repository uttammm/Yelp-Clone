package com.example.yelp

import com.google.gson.annotations.SerializedName

data class YelpSearchResult (
    @SerializedName("total") val total : String,
    @SerializedName("businesses") val restaurants : List<YelpRestaurant>
    )

data class YelpRestaurant (
    val name: String,
    val rating: String,
    val price: String,
    @SerializedName("review_count") val numReviews : Int,
    @SerializedName("image_url") val imageUrl : String,
    @SerializedName("distance") val distanceInMeters : Double,
    val categories : List<YelpCategory>,
    val location : YelpLocation
){
    fun displayDistance() : String {
        val milesPerMeter = 0.000621371
        val distanceInMiles = "%.2f".format(distanceInMeters*milesPerMeter)
        return "$distanceInMiles mi"
    }
}

data class YelpCategory (
    val title: String
    )

data class YelpLocation(
    @SerializedName("display_address") val address : List<String>
)


