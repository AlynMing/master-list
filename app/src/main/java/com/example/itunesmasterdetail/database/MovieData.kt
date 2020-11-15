package com.example.itunesmasterdetail.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieData(
    @PrimaryKey @ColumnInfo(name = "artistId") val artistId: Int = 0,
    @ColumnInfo(name = "collectionId") val collectionId: Int,
    @ColumnInfo(name = "trackId") val trackId: Int,
    @ColumnInfo(name = "trackName") val trackName: String?,
    @ColumnInfo(name = "artworkUrl30") val artworkUrl30: String?,
    @ColumnInfo(name = "artworkUrl60") val artworkUrl60: String?,
    @ColumnInfo(name = "artworkUrl100") val artworkUrl100: String?,
    @ColumnInfo(name = "collectionPrice") val collectionPrice: Double,
    @ColumnInfo(name = "trackPrice") val trackPrice: Double,
    @ColumnInfo(name = "trackRentalPrice") val trackRentalPrice: Double,
    @ColumnInfo(name = "collectionHdPrice") val collectionHdPrice: Double,
    @ColumnInfo(name = "trackHdPrice") val trackHdPrice: Double,
    @ColumnInfo(name = "trackHdRentalPrice") val trackHdRentalPrice: Double,
    @ColumnInfo(name = "currency") val currency: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "primaryGenreName") val primaryGenreName: String?,
    @ColumnInfo(name = "artistName") val artistName: String?,
    @ColumnInfo(name = "collectionName") val collectionName: String?,
    @ColumnInfo(name = "wrapperType") val wrapperType: String?,
    @ColumnInfo(name = "kind") val kind: String?,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?,
    @ColumnInfo(name = "trackTimeMillis") val trackTimeMillis: Long,
    @ColumnInfo(name = "collectionExplicitness") val collectionExplicitness: String?,
    @ColumnInfo(name = "trackExplicitness") val trackExplicitness: String?,
    @ColumnInfo(name = "shortDescription") val shortDescription: String?,
    @ColumnInfo(name = "longDescription") val longDescription: String?) {
}