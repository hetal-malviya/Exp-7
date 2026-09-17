package com.example.exp_7

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.imageview.ShapeableImageView

class ExploreAdapter(context: Context, private var items: List<ExploreItem>) :
    ArrayAdapter<ExploreItem>(context, R.layout.list_item_explore, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_explore, parent, false)
        val item = getItem(position)

        item?.let {
            val imageView = view.findViewById<ShapeableImageView>(R.id.item_image)
            val titleView = view.findViewById<TextView>(R.id.item_title)
            val descView = view.findViewById<TextView>(R.id.item_description)
            val catView = view.findViewById<TextView>(R.id.item_category)
            val favStar = view.findViewById<ImageView>(R.id.item_fav_star)

            imageView.setImageResource(it.imageRes)
            // Set a light background for the image to make it look like a "proper" thumbnail
            val catColor = ExploreRepository.getCategoryColor(context, it.category)
            imageView.setBackgroundColor(catColor)
            imageView.backgroundTintList = ColorStateList.valueOf(catColor).withAlpha(30)
            imageView.setPadding(16, 16, 16, 16)
            
            imageView.imageTintList = ColorStateList.valueOf(catColor)
            
            titleView.text = it.title
            descView.text = it.description
            catView.text = it.category
            
            // Show star if it's a favorite
            favStar.visibility = if (it.isFavorite) View.VISIBLE else View.GONE
            
            // Set category background color
            catView.backgroundTintList = ColorStateList.valueOf(catColor)
            catView.setTextColor(Color.WHITE)
        }

        return view
    }

    fun updateData(newItems: List<ExploreItem>) {
        this.items = newItems
        clear()
        addAll(newItems)
        notifyDataSetChanged()
    }
}