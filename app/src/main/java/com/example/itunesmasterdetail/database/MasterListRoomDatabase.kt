package com.example.itunesmasterdetail.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Database class that invokes the creation of this app's database
 * This is initialized on the MasterListApplication class as a dependency
 */
@Database(entities = [MovieData::class], version = 2, exportSchema = false)
abstract class MasterListRoomDatabase: RoomDatabase() {

    abstract fun movieListDao(): MovieDataDao

    companion object {

         @Volatile
         private var INSTANCE: MasterListRoomDatabase? = null

        @JvmField
        val MIGRATION_1_2: Migration = MigrationVersion(1,2)

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE= it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MasterListRoomDatabase::class.java, "moviedata_database")
                .addMigrations(MIGRATION_1_2)
                .build()


    }

    /**
     * A migration class that describes the changes on the database.
     * On this case primary key was changed from artistId to trackId.
     * This is necessary to avoid IllegalStateException when running the
     * app again while there is a change on the  database
     */
    class MigrationVersion(var previousVersion: Int,
                                 var NextVersion: Int) : Migration(previousVersion, NextVersion) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Since we didn't alter the table, there's nothing else to do here.
            //Action must be done here if schemas are edited
            database.execSQL(
                "CREATE TABLE movie_table_new (trackId INTEGER, collectionId INTEGER, trackName TEXT, " +
                        "artworkUrl30 TEXT, artworkUrl60 TEXT, artworkUrl100 TEXT, " +
                        "collectionPrice REAL, trackPrice REAL, currency TEXT, country TEXT, primaryGenreName TEXT, " +
                        "artistName TEXT, collectionName TEXT, wrapperType TEXT, kind TEXT, releaseDate TEXT, " +
                        "trackTimeMillis INTEGER, shortDescription TEXT, longDescription TEXT, PRIMARY KEY(trackId))");
            // Copy the data
            database.execSQL(
                "INSERT INTO users_new (trackId, collectionId, trackName, artworkUrl30," +
                        "artworkUrl60, artworkUrl100, collectionPrice, trackPrice" +
                        "currency, country, primaryGenreName, artistName, collectionName," +
                        "wrapperType, kind, releaseDate, trackTimeMillis, shortDescription," +
                        "longDescription) SELECT trackId, collectionId, trackName, artworkUrl30," +
                        "artworkUrl60, artworkUrl100, collectionPrice, trackPrice, currency" +
                        "country, primaryGenreName, artistName, collectionName, wrapperType " +
                        "kind, releaseDate, trackTimeMillis, shortDescription, longDescription FROM movie_table");
                        // Remove the old table
                        database.execSQL("DROP TABLE movie_table");
            // Change the table name to the correct one
            database.execSQL("ALTER TABLE movie_table_new RENAME TO movie_table");
        }

    }


}