package com.example.itunesmasterdetail.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.example.itunesmasterdetail.R
import com.example.itunesmasterdetail.SaveDataPreference
import com.example.itunesmasterdetail.models.SearchResultDto
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [HomeActivity].
 */
class ItemDetailActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val sharedPref by instance<SaveDataPreference>()

    lateinit var searchItem: SearchResultDto.SearchItemDto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don"t need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            searchItem = intent.getSerializableExtra(ItemDetailFragment.SEARCH_ITEM) as SearchResultDto.SearchItemDto
            val fragment = ItemDetailFragment()
                .apply {
                arguments = Bundle().apply {
                    putSerializable(ItemDetailFragment.SEARCH_ITEM, searchItem)
                }

            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit()

        }
        savedInstanceState?.putSerializable(ItemDetailFragment.SEARCH_ITEM, searchItem)

    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {

                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, HomeActivity::class.java))

                    true
                }
                else -> super.onOptionsItemSelected(item)
            }


}