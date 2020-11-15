package com.example.itunesmasterdetail.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.itunesmasterdetail.models.SearchAPIListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceDataSourceImpl(private val service: Service) : ServiceDataSource  {

    var _resultsList = MutableLiveData<List<SearchAPIListResponse.SearchResultItem>>()
    override val resultsList: LiveData<List<SearchAPIListResponse.SearchResultItem>>
        get() = _resultsList


    override fun fetchList(options: Map<String, String>) {
        Log.d("ServiceDataSource", "Invoking list")
        var call: Call<SearchAPIListResponse> = service.getList(options)

        call.enqueue(object: Callback<SearchAPIListResponse>{
            override fun onFailure(call: Call<SearchAPIListResponse>, t: Throwable) {
                Log.d("ERROR", "Failure in calling the github response")
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<SearchAPIListResponse>,
                response: Response<SearchAPIListResponse>
            ) {
                parseResult(response)
            }

        })

    }

    private fun parseResult(response: Response<SearchAPIListResponse>){
        if(response.isSuccessful) {
            this._resultsList.postValue(response.body()!!.results)
        } else {
            Log.d("ERROR", " Error Response: " + response.errorBody().toString())
        }
    }

}