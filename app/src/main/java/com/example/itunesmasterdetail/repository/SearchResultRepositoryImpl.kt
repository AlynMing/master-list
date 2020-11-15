package com.example.itunesmasterdetail.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.network.ConnectivityInterceptorImpl
import com.example.itunesmasterdetail.models.SearchAPIListResponse
import com.example.itunesmasterdetail.models.SearchResultDto
import com.example.itunesmasterdetail.service.Service
import com.example.itunesmasterdetail.service.ServiceDataSource

class SearchResultRepositoryImpl(private val serviceDataSource: ServiceDataSource): SearchResultRepository {

    var _searchResult = MutableLiveData<List<SearchResultDto.SearchItemDto>>()
    override val searchRepositoryResult: LiveData<List<SearchResultDto.SearchItemDto>>
    get() = _searchResult

    init {
        serviceDataSource.resultsList.observeForever { newData ->
            _searchResult.postValue(SearchResultDto.create(newData))
        }
    }

    override fun fetchITuneList(options: Map<String, String>, context: Context) {
//           var service: Service = Service.invoke(ConnectivityInterceptorImpl(context))
        serviceDataSource.fetchList(options)

    }

    override fun fetchITuneList(options: Map<String, String>) {
        serviceDataSource.fetchList(options)
    }
}