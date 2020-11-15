package com.example.itunesmasterdetail.service

import com.example.githubuser.network.ConnectivityInterceptor
import com.example.githubuser.network.LimitExceedInterceptorImpl
import com.example.itunesmasterdetail.models.SearchAPIListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface Service {

//     private val baseUrl: String = "https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie&amp;all"

    @GET("search")
    fun getList(@QueryMap options: Map<String, String>): Call<SearchAPIListResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): Service {

            var interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            var okClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor) // Avoid crashing when there is no internet
                .addInterceptor(interceptor).build()

            return Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okClient)
                .build().create(Service::class.java)


        }
    }

}