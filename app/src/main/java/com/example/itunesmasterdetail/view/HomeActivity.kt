package com.example.itunesmasterdetail.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.example.itunesmasterdetail.R
import com.example.itunesmasterdetail.SaveDataPreference
import com.example.itunesmasterdetail.base.ScopeActivity

import com.example.itunesmasterdetail.models.SearchResultDto
import com.example.itunesmasterdetail.utils.TimeUtilities
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class HomeActivity : ScopeActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory by instance<HomeViewModelFactory>()
    private val sharedPref by instance<SaveDataPreference>()

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private lateinit var homeViewModel: HomeViewModel
    private var searchList: List<SearchResultDto.SearchItemDto> = ArrayList()
    private lateinit var adapter: SearchItemListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var skeletonScreen: RecyclerViewSkeletonScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setUpDateOnTitleBar()

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView()
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        initFetchData()


    }

    private fun initFetchData() = launch {
        showShimmer()
        homeViewModel.fetchSearchList()
        initUpdateListView()
    }

    private fun initUpdateListView() {
        homeViewModel.searchResult.observe(this, Observer { searchList ->
            if(searchList== null) return@Observer
            hideShimmer()
            if(!searchList.isNullOrEmpty()) {
                this.recyclerView.post(Runnable {
                    adapter.updateList(searchList)
                })
            }
        })
    }

    private fun setUpDateOnTitleBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        if(sharedPref.getLastViewedDate().isNullOrEmpty()) {
            toolbar.title = TimeUtilities.getLocalDateTimeString(sharedPref.getLastViewedDate())
        } else {
            sharedPref.setLastViewedDate(TimeUtilities.getCurrentTimeStamp().toString())
            toolbar.title = TimeUtilities.getLocalDateTimeString()
        }
        setSupportActionBar(toolbar)
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


    private fun showShimmer() {
            this.skeletonScreen = Skeleton.bind(this.recyclerView)
                .adapter(adapter)
                .load(R.layout.layout_default_item_skeleton)
                .shimmer(true)
                .show()
    }

    private fun hideShimmer(){
        skeletonScreen?.hide()
    }

}