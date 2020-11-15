package com.example.itunesmasterdetail.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.itunesmasterdetail.R
import com.example.itunesmasterdetail.dummy.DummyContent
import com.example.itunesmasterdetail.models.SearchResultDto

class SearchItemListAdapter(private val parentActivity: ItemListActivity,
                            private val values: List<SearchResultDto.SearchItemDto>,
                            private val twoPane: Boolean) :
    RecyclerView.Adapter<SearchItemListAdapter.SearchItemViewHolder>() {

    private val onClickListener: View.OnClickListener
    private val listItems: MutableList<SearchResultDto.SearchItemDto> = values.toMutableList()

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as SearchResultDto.SearchItemDto
            if (twoPane) {
                val fragment = ItemDetailFragment()
                    .apply {
                        arguments = Bundle().apply {
                            putSerializable(ItemDetailFragment.SEARCH_ITEM, item)
                        }
                    }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    putExtra(ItemDetailFragment.SEARCH_ITEM, item)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return SearchItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val item = listItems[position]
        holder.bindData(item)
    }

    override fun getItemCount() = listItems.size

    fun updateList(searchListParam: List<SearchResultDto.SearchItemDto>) {
        this.listItems.clear()
        this.listItems.addAll(searchListParam)
        notifyDataSetChanged()
    }

    inner class SearchItemViewHolder(viewParam: View) : RecyclerView.ViewHolder(viewParam) {

        val view: View = viewParam
        val image: ImageView = view.findViewById(R.id.track_image)
        val trackName: TextView = view.findViewById(R.id.track_name_value)
        val priceValue: TextView = view.findViewById(R.id.price_value)
        val genre: TextView = view.findViewById(R.id.genre_value)

        fun bindData(searchItem: SearchResultDto.SearchItemDto) {
            Glide.with(view)
                .load(searchItem.artworkUrl100)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(image)

            trackName.text = searchItem.trackName
            priceValue.text = searchItem.trackPrice.toString()
            genre.text = searchItem.primaryGenreName

            with(itemView) {
                tag = searchItem
                setOnClickListener(onClickListener)
            }

        }

    }

}