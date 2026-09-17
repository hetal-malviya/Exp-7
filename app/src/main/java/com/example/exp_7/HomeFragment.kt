package com.example.exp_7

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var adapter: ExploreAdapter
    private lateinit var listView: ListView
    private lateinit var emptyState: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        listView = view.findViewById(R.id.explore_list)
        emptyState = view.findViewById(R.id.empty_state)
        val searchView = view.findViewById<SearchView>(R.id.search_view)

        adapter = ExploreAdapter(requireContext(), ExploreRepository.getAllItems())
        listView.adapter = adapter

        setupFeaturedAdventure(view)

        listView.setOnItemClickListener { _, _, position, _ ->
            val item = adapter.getItem(position)
            navigateToDetail(item)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                val filtered = ExploreRepository.searchItems(newText ?: "")
                adapter.updateData(filtered)
                emptyState.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
                return true
            }
        })

        return view
    }

    private fun setupFeaturedAdventure(view: View) {
        val featuredItem = ExploreRepository.getFeaturedItem()
        val featuredImage = view.findViewById<ImageView>(R.id.featured_image)
        val featuredTitle = view.findViewById<TextView>(R.id.featured_title)
        val featuredDesc = view.findViewById<TextView>(R.id.featured_desc)
        val btnExplore = view.findViewById<View>(R.id.btn_featured_explore)
        val featuredCard = view.findViewById<View>(R.id.featured_card)

        featuredTitle.text = featuredItem.title
        featuredDesc.text = featuredItem.description
        featuredImage.setImageResource(featuredItem.imageRes)
        featuredImage.imageTintList = android.content.res.ColorStateList.valueOf(
            ExploreRepository.getCategoryColor(requireContext(), featuredItem.category)
        )

        val clickListener = View.OnClickListener {
            navigateToDetail(featuredItem)
        }
        
        btnExplore.setOnClickListener(clickListener)
        featuredCard.setOnClickListener(clickListener)
    }

    private fun navigateToDetail(item: ExploreItem?) {
        item?.let {
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("item", it)
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}