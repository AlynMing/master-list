package com.example.itunesmasterdetail.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDataDao {

    @Query("SELECT * from movie_table")
    fun getMovieList() : List<MovieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieData: MovieData)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()

}