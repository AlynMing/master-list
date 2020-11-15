package com.example.itunesmasterdetail.view

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.network.ConnectivityInterceptorImpl
import com.example.itunesmasterdetail.models.SearchResultDto
import com.example.itunesmasterdetail.repository.SearchResultRepository
import com.example.itunesmasterdetail.repository.SearchResultRepositoryImpl
import com.example.itunesmasterdetail.service.Service
import com.example.itunesmasterdetail.service.ServiceDataSourceImpl
import com.example.itunesmasterdetail.utils.Constants

class HomeViewModel(context: Context): ViewModel() {


    val service: Service = Service.invoke(ConnectivityInterceptorImpl(context))
    private val searchResultRepository: SearchResultRepository = SearchResultRepositoryImpl(ServiceDataSourceImpl(service))
    var searchResult: LiveData<List<SearchResultDto.SearchItemDto>> = searchResultRepository.searchRepositoryResult


    fun fetchSearchList(){
        val queryMap: MutableMap<String, String> =
            HashMap()
        queryMap["term"] = Constants.TERM
        queryMap["country"] = Constants.COUNTRY
        queryMap["media"] = Constants.MEDIA
        queryMap["all"] = Constants.ALL

        Log.d("ServiceDataSource", "Invoking list in Home View model ")
        searchResultRepository.fetchITuneList(queryMap)

    }

}