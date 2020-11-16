package com.example.itunesmasterdetail.models

import android.util.Log
import com.example.itunesmasterdetail.database.MovieData
import java.io.Serializable

open class SearchResultDto {

    companion object {

        private fun parseInfoData(searchItem: SearchAPIListResponse.SearchResultItem): SearchItemDto {
            val searchItemDto = SearchItemDto()
            searchItem.collectionId?.let { searchItemDto.collectionId = it }
            searchItem.trackId?.let { searchItemDto.trackId = it }
            searchItem.trackName?.let { searchItemDto.trackName = it }
            searchItem.artworkUrl30?.let { searchItemDto.artworkUrl30 = it }
            searchItem.artworkUrl60?.let { searchItemDto.artworkUrl60 = it }
            searchItem.artworkUrl100?.let { searchItemDto.artworkUrl100 = it }
            searchItem.collectionPrice?.let { searchItemDto.collectionPrice = it }
            searchItem.trackPrice?.let { searchItemDto.trackPrice = it }
            searchItem.trackRentalPrice?.let { searchItemDto.trackRentalPrice = it }
            searchItem.collectionHdPrice?.let { searchItemDto.collectionHdPrice = it }
            searchItem.trackHdPrice?.let { searchItemDto.trackHdPrice = it }
            searchItem.trackHdRentalPrice?.let { searchItemDto.trackHdRentalPrice = it }
            searchItem.currency?.let { searchItemDto.currency = it }
            searchItem.country?.let { searchItemDto.country = it }
            searchItem.primaryGenreName?.let { searchItemDto.primaryGenreName = it }
            searchItem.artistName?.let { searchItemDto.artistName = it }
            searchItem.collectionName?.let { searchItemDto.collectionName = it }
            searchItem.wrapperType?.let { searchItemDto.wrapperType = it }
            searchItem.kind?.let { searchItemDto.kind = it }
            searchItem.releaseDate?.let { searchItemDto.releaseDate = it }
            searchItem.trackTimeMillis?.let { searchItemDto.trackTimeMillis = it }
            searchItem.collectionExplicitness?.let { searchItemDto.collectionExplicitness = it }
            searchItem.trackExplicitness?.let { searchItemDto.trackExplicitness = it }
            searchItem.shortDescription?.let { searchItemDto.shortDescription = it }
            searchItem.longDescription?.let { searchItemDto.longDescription = it }

            return searchItemDto

        }

        private fun parseInfoDataFromDb(searchItemData: MovieData): SearchItemDto {
            val searchItemDto = SearchItemDto()
            searchItemData.collectionId?.let { searchItemDto.collectionId = it }
            searchItemData.trackId?.let { searchItemDto.trackId = it }
            searchItemData.trackName?.let { searchItemDto.trackName = it }
            searchItemData.artworkUrl30?.let { searchItemDto.artworkUrl30 = it }
            searchItemData.artworkUrl60?.let { searchItemDto.artworkUrl60 = it }
            searchItemData.artworkUrl100?.let { searchItemDto.artworkUrl100 = it }
            searchItemData.collectionPrice?.let { searchItemDto.collectionPrice = it }
            searchItemData.trackPrice?.let { searchItemDto.trackPrice = it }
            searchItemData.trackRentalPrice?.let { searchItemDto.trackRentalPrice = it }
            searchItemData.collectionHdPrice?.let { searchItemDto.collectionHdPrice = it }
            searchItemData.trackHdPrice?.let { searchItemDto.trackHdPrice = it }
            searchItemData.trackHdRentalPrice?.let { searchItemDto.trackHdRentalPrice = it }
            searchItemData.currency?.let { searchItemDto.currency = it }
            searchItemData.country?.let { searchItemDto.country = it }
            searchItemData.primaryGenreName?.let { searchItemDto.primaryGenreName = it }
            searchItemData.artistName?.let { searchItemDto.artistName = it }
            searchItemData.collectionName?.let { searchItemDto.collectionName = it }
            searchItemData.wrapperType?.let { searchItemDto.wrapperType = it }
            searchItemData.kind?.let { searchItemDto.kind = it }
            searchItemData.releaseDate?.let { searchItemDto.releaseDate = it }
            searchItemData.trackTimeMillis?.let { searchItemDto.trackTimeMillis = it }
            searchItemData.collectionExplicitness?.let { searchItemDto.collectionExplicitness = it }
            searchItemData.trackExplicitness?.let { searchItemDto.trackExplicitness = it }
            searchItemData.shortDescription?.let { searchItemDto.shortDescription = it }
            searchItemData.longDescription?.let { searchItemDto.longDescription = it }

            return searchItemDto
        }


        @JvmStatic
        fun create(resultsFromApi: List<SearchAPIListResponse.SearchResultItem>): MutableList<SearchItemDto> {
            var resultList: MutableList<SearchItemDto> = ArrayList()
            for(resultItem in resultsFromApi){
                Log.d("Parsing API", "Parsing API Artist Name: ".plus(resultItem.artistName))
                resultList.add(parseInfoData(resultItem))
            }

            return resultList
        }

        @JvmStatic
        fun createFromDb(resultsFromDb: List<MovieData>): MutableList<SearchItemDto> {
            var resultList: MutableList<SearchItemDto> = ArrayList()
            for(resultItem in resultsFromDb){
                Log.d("Parsing DB", "Parsing Track Id: ".plus(resultItem.trackId))
                resultList.add(parseInfoDataFromDb(resultItem))
            }

            return resultList
        }

    }

    open class SearchItemDto(): Serializable {
        var artistId: Long = 0
        var collectionId: Int = 0
        var trackId: Int = 0
        var trackName: String = ""
        var artworkUrl30: String = ""
        var artworkUrl60: String = ""
        var artworkUrl100: String = ""
        var collectionPrice: Double = 0.0
        var trackPrice: Double = 0.0
        var trackRentalPrice: Double = 0.0
        var collectionHdPrice: Double = 0.0
        var trackHdPrice: Double = 0.0
        var trackHdRentalPrice: Double = 0.0
        var currency: String = ""
        var country: String = ""
        var primaryGenreName: String = ""
        var artistName: String = ""
        var collectionName: String = ""
        var wrapperType: String = ""
        var kind: String = ""
        var releaseDate: String = ""
        var trackTimeMillis: Long = 0
        var collectionExplicitness: String = ""
        var trackExplicitness: String = ""
        var shortDescription: String = ""
        var longDescription: String = ""

    }

}