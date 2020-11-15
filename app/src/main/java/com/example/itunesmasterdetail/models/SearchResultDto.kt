package com.example.itunesmasterdetail.models

import android.util.Log
import okhttp3.internal.userAgent
import java.io.Serializable

open class SearchResultDto {

    companion object {

        private fun parseInfoData(searchItem: SearchAPIListResponse.SearchResultItem): SearchItemDto {
            return SearchItemDto(searchItem.artistId, searchItem.collectionId, searchItem.trackId,
                                 searchItem.trackName, searchItem.artworkUrl30, searchItem.artworkUrl60,
                                 searchItem.artworkUrl100, searchItem.collectionPrice, searchItem.trackPrice,
                                 searchItem.trackRentalPrice, searchItem.collectionHdPrice,
                                 searchItem.trackHdPrice, searchItem.trackHdRentalPrice,
                                 searchItem.currency, searchItem.country, searchItem.primaryGenreName,
                                 searchItem.artistName, searchItem.collectionName, searchItem.wrapperType,
                                 searchItem.kind, searchItem.releaseDate, searchItem.trackTimeMillis,
                                 searchItem.collectionExplicitness, searchItem.trackExplicitness,
                                 searchItem.shortDescription, searchItem.longDescription)
        }

        @JvmStatic
        fun create(results: List<SearchAPIListResponse.SearchResultItem>): MutableList<SearchItemDto> {
            var resultList: MutableList<SearchItemDto> = ArrayList()
            for(resultItem in results){
                Log.d("Parsing", "Parsing Artist Name: ".plus(resultItem.artistName))
                resultList.add(parseInfoData(resultItem))
            }

            return resultList
        }

    }

    open class SearchItemDto(var artistId: Int = 0, var collectionId: Int = 0, var trackId: Int = 0,
                             var trackName: String = "", var artworkUrl30: String = "", var artworkUrl60: String = "",
                             var artworkUrl100: String = "", var collectionPrice: Double = 0.0, var trackPrice: Double = 0.0,
                             var trackRentalPrice: Double = 0.0, var collectionHdPrice: Double = 0.0,
                             var trackHdPrice: Double = 0.0, var trackHdRentalPrice: Double = 0.0,
                             var currency: String = "", var country: String = "", var primaryGenreName: String = "",
                             var artistName: String = "", var collectionName: String = "", var wrapperType: String = "",
                             var kind: String = "", var releaseDate: String = "", var trackTimeMillis: Long = 0,
                             var collectionExplicitness: String = "", var trackExplicitness: String = "",
                             var shortDescription: String = "", var longDescription: String = ""): Serializable { }

}