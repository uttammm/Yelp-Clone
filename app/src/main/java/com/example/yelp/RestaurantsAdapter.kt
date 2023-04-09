package com.example.yelp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


class RestaurantsAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {
    private lateinit var tvName: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var tvAddress: TextView
    private lateinit var tvCategory: TextView
    private lateinit var image: ImageView
    private lateinit var numReviews: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvDistance: TextView
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: YelpRestaurant) {
            tvName = itemView.findViewById(R.id.tvName)
            ratingBar = itemView.findViewById(R.id.ratingBar)
            tvAddress = itemView.findViewById(R.id.tvAddress)
            tvCategory = itemView.findViewById(R.id.tvCategory)
            image = itemView.findViewById(R.id.image)
            numReviews = itemView.findViewById(R.id.numReviews)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            tvDistance = itemView.findViewById(R.id.tvDistance)

            tvName.text = restaurant.name
            ratingBar.rating = restaurant.rating.toFloat()
            tvAddress.text = restaurant.location.address[0]
            tvCategory.text = restaurant.categories[0].title
            numReviews.text = restaurant.numReviews.toString()
            tvPrice.text = restaurant.price
            tvDistance.text = restaurant.displayDistance()
            Glide.with(context).load(restaurant.imageUrl).transform(CenterCrop(),RoundedCorners(20)).override(250,250).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

}
