package com.example.itunesmasterdetail.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.network.ConnectivityInterceptorImpl
import com.example.itunesmasterdetail.database.MovieData
import com.example.itunesmasterdetail.database.MovieDataDao
import com.example.itunesmasterdetail.models.SearchAPIListResponse
import com.example.itunesmasterdetail.models.SearchResultDto
import com.example.itunesmasterdetail.service.Service
import com.example.itunesmasterdetail.service.ServiceDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchResultRepositoryImpl(private val movieDataDao: MovieDataDao,
                                 private val serviceDataSource: ServiceDataSource): SearchResultRepository {

    var _searchResult = MutableLiveData<List<SearchResultDto.SearchItemDto>>()
    override val searchRepositoryResult: LiveData<List<SearchResultDto.SearchItemDto>>
    get() = _searchResult


    override suspend fun fetchITuneList(options: Map<String, String>) {
        GlobalScope.launch(Dispatchers.IO ) {
            if (!movieDataDao.getMovieList().isNullOrEmpty()) {
                Log.d("SearchResultRepository", "SearchResultRepository: Getting from persistence")
                _searchResult.postValue(SearchResultDto.createFromDb(movieDataDao.getMovieList()))
            } else {
                Log.d("SearchResultRepository", "SearchResultRepository: Getting from API")
                serviceDataSource.fetchList(options)
                persistResponse()
            }
        }
    }

    private fun persistResponse() {
        GlobalScope.launch(Dispatchers.Main ) {
            serviceDataSource.resultsList.observeForever { newData ->
                val sortedResult = newData.sortedBy { fetchedDataItem -> fetchedDataItem.trackId }
                _searchResult.postValue(SearchResultDto.create(sortedResult))
                persistFetchedData(newData)
            }
        }

    }

    private fun persistFetchedData(resultsFromApi: List<SearchAPIListResponse.SearchResultItem>) {
        GlobalScope.launch(Dispatchers.IO ) {
            for (movieDataApiItem in resultsFromApi) {
                Log.d("SearchResultRepository", "SearchResultRepository: Persisting data ".plus(movieDataApiItem.trackId))
                movieDataDao.insert(parseInfoData(movieDataApiItem))
            }
        }

    }

    private fun parseInfoData(searchItem: SearchAPIListResponse.SearchResultItem): MovieData {

        return MovieData(searchItem.trackId, searchItem.collectionId,
        searchItem.trackName?.let { searchItem.trackName }, searchItem.artworkUrl30, searchItem.artworkUrl60,
        searchItem.artworkUrl100, searchItem.collectionPrice, searchItem.trackPrice,
        searchItem.trackRentalPrice, searchItem.collectionHdPrice,
        searchItem.trackHdPrice, searchItem.trackHdRentalPrice,
        searchItem.currency, searchItem.country, searchItem.primaryGenreName,
        searchItem.artistName, searchItem.collectionName, searchItem.wrapperType,
        searchItem.kind, searchItem.releaseDate, searchItem.trackTimeMillis,
        searchItem.collectionExplicitness, searchItem.trackExplicitness,
        searchItem.shortDescription, searchItem.longDescription)

    }



}