package com.example.yelp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.LinearLayoutManager

private const val TAG = "MainActivity"
private const val API_KEY = "cjETFK7ZIr-MYiwrZM9trO_kXD3r-cP-F911BaYWdPgCYEC5tav9oe1z9DiLRkolve0nMlp1oJnO7ZiwU_WdIdKFvblOZUoRJB5FzYZYF3wGlQSr2tpQLJtND6nmY3Yx"
private const val BASE_URL = "https://api.yelp.com/v3/"

private lateinit var rvRestaurants : RecyclerView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvRestaurants = findViewById(R.id.rvRestaurants)

        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter = RestaurantsAdapter(this, restaurants)
        rvRestaurants.adapter = adapter
        rvRestaurants.layoutManager = LinearLayoutManager(this)



        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val yelpService = retrofit.create(YelpService::class.java)
        val data = yelpService.searchRestaurants("Bearer $API_KEY","Avocado Toast", "New York").enqueue(object:
            Callback<YelpSearchResult>{
            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>){
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if(body == null){
                    Log.w(TAG, "something has gone wrong, null body returned")
                    return
                }
                restaurants.addAll(body.restaurants)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }

        })
    }

}