package com.example.exp_7

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class FavoritesFragment : Fragment() {

    private lateinit var adapter: ExploreAdapter
    private lateinit var listView: ListView
    private lateinit var emptyState: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        listView = view.findViewById(R.id.fav_list)
        emptyState = view.findViewById(R.id.empty_state_fav)
        val btnBack = view.findViewById<View>(R.id.btn_back_fav)

        btnBack.setOnClickListener {
            (activity as? MainActivity)?.switchToHome()
        }

        adapter = ExploreAdapter(requireContext(), ExploreRepository.getFavoriteItems())
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val item = adapter.getItem(position)
            item?.let {
                val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                    putExtra("item", it)
                }
                startActivity(intent)
            }
        }

        updateUI()
        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(ExploreRepository.getFavoriteItems())
        updateUI()
    }

    private fun updateUI() {
        val favorites = ExploreRepository.getFavoriteItems()
        emptyState.visibility = if (favorites.isEmpty()) View.VISIBLE else View.GONE
        listView.visibility = if (favorites.isEmpty()) View.GONE else View.VISIBLE
    }
}