package com.example.itunesmasterdetail.view

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.itunesmasterdetail.R
import com.example.itunesmasterdetail.dummy.DummyContent
import com.example.itunesmasterdetail.models.SearchResultDto
import org.w3c.dom.Text

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: SearchResultDto.SearchItemDto? = null
    private lateinit var movieTitle: TextView
    private lateinit var movieArtist: TextView
    private lateinit var movieDescription: TextView
    private lateinit var moviePoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(SEARCH_ITEM)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getSerializable(SEARCH_ITEM) as SearchResultDto.SearchItemDto
                val actionBar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
                actionBar?.title = item?.trackName

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        setUpViews(rootView)

        // Show the dummy content as text in a TextView.
        item?.let {
            this.movieTitle.text = it.trackName
            this.movieArtist.text = it.artistName
            this.movieDescription.text = it.longDescription

            Glide.with(activity!!)
                .load(it.artworkUrl100)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(this.moviePoster)
        }

        return rootView
    }

    private fun setUpViews(rootView: View){
         this.moviePoster = rootView.findViewById<ImageView>(R.id.movie_poster_detail)
         this.movieTitle = rootView.findViewById(R.id.item_title)
         this.movieArtist = rootView.findViewById(R.id.item_artist)
         this.movieDescription = rootView.findViewById(R.id.item_description)
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
        const val SEARCH_ITEM = "search_item"
    }
}