package com.example.itunesmasterdetail.view

import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesmasterdetail.R

import com.example.itunesmasterdetail.models.SearchResultDto

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private lateinit var homeViewModel: HomeViewModel
    private var searchList: List<SearchResultDto.SearchItemDto> = ArrayList()
    private lateinit var adapter: SearchItemListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView()

        homeViewModel = HomeViewModel(this)
        homeViewModel.fetchSearchList()

        homeViewModel.searchResult.observe(this, Observer { searchList ->
            if(searchList== null) return@Observer
            if(!searchList.isNullOrEmpty()) {
                this.recyclerView.post(Runnable {
                    adapter.updateList(searchList)
                })
            }
        })
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.item_list)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        adapter = SearchItemListAdapter(
            this,
            searchList,
            twoPane
        )
        recyclerView.adapter = adapter

    }

}