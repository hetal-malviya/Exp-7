package com.example.exp_7

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val item = intent.getSerializableExtra("item") as? ExploreItem ?: return

        val imageView = findViewById<ImageView>(R.id.detail_image)
        val titleView = findViewById<TextView>(R.id.detail_title)
        val categoryView = findViewById<TextView>(R.id.detail_category)
        val descView = findViewById<TextView>(R.id.detail_description)
        val fabFavorite = findViewById<FloatingActionButton>(R.id.fab_favorite)
        val btnExplore = findViewById<MaterialButton>(R.id.btn_explore)
        val btnBack = findViewById<ImageButton>(R.id.btn_back_detail)
        val btnBackBottom = findViewById<MaterialButton>(R.id.btn_back_bottom)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        
        // Handle window insets for the back button to avoid status bar overlap
        ViewCompat.setOnApplyWindowInsetsListener(btnBack) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = systemBars.top + (12 * resources.displayMetrics.density).toInt()
            }
            insets
        }

        btnBack.setOnClickListener { 
            onBackPressedDispatcher.onBackPressed()
        }

        btnBackBottom.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        imageView.setImageResource(item.imageRes)
        imageView.setBackgroundColor(ExploreRepository.getCategoryColor(this, item.category))
        imageView.backgroundTintList = android.content.res.ColorStateList.valueOf(
            ExploreRepository.getCategoryColor(this, item.category)
        ).withAlpha(40)
        imageView.setPadding(64, 64, 64, 64)
        
        titleView.text = item.title
        categoryView.text = item.category
        descView.text = item.longDescription

        updateFavoriteIcon(fabFavorite, item.isFavorite)

        fabFavorite.setOnClickListener {
            ExploreRepository.toggleFavorite(item.id)
            item.isFavorite = !item.isFavorite
            updateFavoriteIcon(fabFavorite, item.isFavorite)
            val message = if (item.isFavorite) "Added to favorites" else "Removed from favorites"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        btnExplore.setOnClickListener {
            Toast.makeText(this, "Exploring ${item.title}...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFavoriteIcon(fab: FloatingActionButton, isFavorite: Boolean) {
        fab.setImageResource(if (isFavorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)
        // Highlight the star with vibrant yellow when favorited
        val tintColor = if (isFavorite) 0xFFFFD600.toInt() else android.graphics.Color.WHITE
        fab.imageTintList = android.content.res.ColorStateList.valueOf(tintColor)
    }
}