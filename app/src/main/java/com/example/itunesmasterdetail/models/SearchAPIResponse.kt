package com.example.itunesmasterdetail.models

data class SearchAPIListResponse(var results: MutableList<SearchResultItem>) {

    data class SearchResultItem(
        var artistId: Long,
        var collectionId: Int,
        var trackId: Int,
        var trackName: String?,
        var artworkUrl30: String?,
        var artworkUrl60: String?,
        var artworkUrl100: String?,
        var collectionPrice: Double,
        var trackPrice: Double,
        var trackRentalPrice: Double,
        var collectionHdPrice: Double,
        var trackHdPrice: Double,
        var trackHdRentalPrice: Double,
        var currency: String ?,
        var country: String?,
        var primaryGenreName: String?,
        var artistName: String?,
        var collectionName: String?,
        var wrapperType: String?,
        var kind: String?,
        var releaseDate: String?,
        var trackTimeMillis: Long,
        var collectionExplicitness: String?,
        var trackExplicitness: String?,
        var shortDescription: String?,
        var longDescription: String?)

}