package com.example.itunesmasterdetail.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.itunesmasterdetail.models.SearchAPIListResponse
import com.example.itunesmasterdetail.models.SearchResultDto

interface SearchResultRepository {

    val searchRepositoryResult: LiveData<List<SearchResultDto.SearchItemDto>>

    suspend fun fetchITuneList(options: Map<String, String>)

}