package com.example.itunesmasterdetail.service

import androidx.lifecycle.LiveData
import com.example.itunesmasterdetail.models.SearchAPIListResponse

interface ServiceDataSource {

    val resultsList: LiveData<List<SearchAPIListResponse.SearchResultItem>>

    suspend fun fetchList(options: Map<String, String>)

}