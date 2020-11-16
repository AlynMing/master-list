package com.example.itunesmasterdetail.view


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.itunesmasterdetail.models.SearchResultDto
import com.example.itunesmasterdetail.repository.SearchResultRepository
import com.example.itunesmasterdetail.utils.Constants

class HomeViewModel(private val movieListRepo: SearchResultRepository): ViewModel() {

    var searchResult: LiveData<List<SearchResultDto.SearchItemDto>> = movieListRepo.searchRepositoryResult


    suspend fun fetchSearchList(){
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap[Constants.TERM_KEY] = Constants.TERM
        queryMap[Constants.COUNTRY_KEY] = Constants.COUNTRY
        queryMap[Constants.MEDIA_KEY] = Constants.MEDIA
        queryMap[Constants.ALL_KEY] = Constants.ALL

        Log.d("ServiceDataSource", "Invoking list in Home View model ")
        movieListRepo.fetchITuneList(queryMap)

    }

}