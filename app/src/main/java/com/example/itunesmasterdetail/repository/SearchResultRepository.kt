package com.example.itunesmasterdetail.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.itunesmasterdetail.models.SearchAPIListResponse
import com.example.itunesmasterdetail.models.SearchResultDto

interface SearchResultRepository {

    val searchRepositoryResult: LiveData<List<SearchResultDto.SearchItemDto>>

    fun fetchITuneList(options: Map<String, String>)

    fun fetchITuneList(options: Map<String, String>, context: Context)

}