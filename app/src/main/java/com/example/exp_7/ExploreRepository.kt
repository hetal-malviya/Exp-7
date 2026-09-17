package com.example.exp_7

object ExploreRepository {
    private val allItems = mutableListOf(
        ExploreItem(1, "Mountain Adventure", "Experience the thrill of the peaks.", 
            "Embark on a journey through the majestic mountains. Breath-taking views and challenging trails await you in this ultimate alpine experience.",
            android.R.drawable.ic_dialog_map, "Adventure"),
        ExploreItem(2, "Beach Escape", "Relax on the golden sands.", 
            "Escape to a tropical paradise where the ocean meets the shore. Enjoy the sun, surf, and serene atmosphere of the world's finest beaches.",
            android.R.drawable.ic_menu_day, "Relaxation"),
        ExploreItem(3, "City Explorer", "Discover urban wonders.", 
            "Navigate the vibrant streets of modern metropolises. From towering skyscrapers to hidden gems, every corner has a story to tell.",
            android.R.drawable.ic_menu_mylocation, "Urban"),
        ExploreItem(4, "Forest Trail", "Connect with nature.", 
            "Walk through ancient woodlands and listen to the symphony of the forest. A peaceful retreat for those seeking tranquility.",
            android.R.drawable.ic_menu_directions, "Nature"),
        ExploreItem(5, "Desert Safari", "Journey through the dunes.", 
            "Experience the vast beauty of the desert. Ride through rolling sands and witness stunning sunsets in a land of golden horizons.",
            android.R.drawable.ic_menu_compass, "Adventure"),
        ExploreItem(6, "Waterfall Trip", "Witness the power of water.", 
            "Visit spectacular waterfalls cascading down lush cliffs. Feel the mist on your face and the thunder in your ears.",
            android.R.drawable.ic_menu_view, "Nature"),
        ExploreItem(7, "Historical Places", "Step back in time.", 
            "Explore ancient ruins and architectural marvels. Learn about the rich history and culture of civilizations that came before us.",
            android.R.drawable.ic_menu_agenda, "Culture"),
        ExploreItem(8, "Wildlife Adventure", "Meet the animals.", 
            "Get up close with nature's most magnificent creatures in their natural habitat. A must for animal lovers and photographers.",
            android.R.drawable.ic_menu_camera, "Nature"),
        ExploreItem(9, "Camping", "Sleep under the stars.", 
            "There's nothing like the great outdoors. Set up camp and enjoy nights by the fire under a canopy of twinkling stars.",
            android.R.drawable.btn_star_big_on, "Outdoor"),
        ExploreItem(10, "Photography Spots", "Capture the moment.", 
            "Find the most photogenic locations in the world. Perfect for enthusiasts looking to capture the beauty of the planet.",
            android.R.drawable.ic_menu_gallery, "Creative")
    )

    fun getAllItems() = allItems

    fun getFavoriteItems() = allItems.filter { it.isFavorite }

    fun toggleFavorite(itemId: Int) {
        allItems.find { it.id == itemId }?.let {
            it.isFavorite = !it.isFavorite
        }
    }
    
    fun searchItems(query: String): List<ExploreItem> {
        return allItems.filter { 
            it.title.contains(query, ignoreCase = true) || 
            it.category.contains(query, ignoreCase = true) 
        }
    }

    fun getFeaturedItem(): ExploreItem = allItems[0] // Returns Mountain Adventure as featured

    fun getCategoryColor(context: android.content.Context, category: String): Int {
        val colorRes = when (category) {
            "Adventure" -> R.color.cat_adventure
            "Nature" -> R.color.cat_nature
            "Urban" -> R.color.cat_urban
            "Relaxation" -> R.color.cat_relaxation
            "Culture" -> R.color.cat_culture
            "Outdoor" -> R.color.cat_outdoor
            "Creative" -> R.color.cat_creative
            else -> R.color.primary
        }
        return androidx.core.content.ContextCompat.getColor(context, colorRes)
    }
}