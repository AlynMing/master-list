package com.example.itunesmasterdetail.models

data class SearchAPIListResponse(var results: List<SearchResultItem>) {

    data class SearchResultItem(
        var artistId: Int = 0,
        var collectionId: Int = 0,
        var trackId: Int = 0,
        var trackName: String = "",
        var artworkUrl30: String = "",
        var artworkUrl60: String = "",
        var artworkUrl100: String = "",
        var collectionPrice: Double = 0.0,
        var trackPrice: Double = 0.0,
        var trackRentalPrice: Double = 0.0,
        var collectionHdPrice: Double = 0.0,
        var trackHdPrice: Double = 0.0,
        var trackHdRentalPrice: Double = 0.0,
        var currency: String = "",
        var country: String = "",
        var primaryGenreName: String = "",
        var artistName: String = "",
        var collectionName: String = "",
        var wrapperType: String = "",
        var kind: String = "",
        var releaseDate: String = "",
        var trackTimeMillis: Long = 0,
        var collectionExplicitness: String = "",
        var trackExplicitness: String = "",
        var shortDescription: String = "",
        var longDescription: String = ""
    )

}