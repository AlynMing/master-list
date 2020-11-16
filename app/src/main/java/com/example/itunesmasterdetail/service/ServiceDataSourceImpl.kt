package com.example.itunesmasterdetail.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.network.LimitExceedException
import com.example.githubuser.network.NoConnectivityException
import com.example.itunesmasterdetail.models.SearchAPIListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceDataSourceImpl(private val service: Service) : ServiceDataSource  {

    private var _resultsList = MutableLiveData<List<SearchAPIListResponse.SearchResultItem>>()
    override val resultsList: LiveData<List<SearchAPIListResponse.SearchResultItem>>
        get() = _resultsList

    override suspend fun fetchList(options: Map<String, String>) {
        Log.d("ServiceDataSource", "Invoking list")
        try {
            val fetchUsersDataList = service.getList(options)
            this._resultsList.postValue(fetchUsersDataList.results)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet")
        }
    }

}