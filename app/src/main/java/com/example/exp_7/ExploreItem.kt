package com.example.exp_7

import java.io.Serializable

data class ExploreItem(
    val id: Int,
    val title: String,
    val description: String,
    val longDescription: String,
    val imageRes: Int,
    val category: String,
    var isFavorite: Boolean = false
) : Serializable