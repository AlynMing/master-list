package com.example.itunesmasterdetail.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [MovieData::class], version = 1, exportSchema = false)
abstract class CustomizeRoomDatabase: RoomDatabase() {

    abstract fun getMovieListDao(): MovieDataDao

    companion object {

         @Volatile
         private var INSTANCE: CustomizeRoomDatabase? = null

        @JvmField
        val MIGRATION_1_2: Migration = MigrationVersion(1,2)

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE= it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CustomizeRoomDatabase::class.java, "moviedata_database")
                .addMigrations(MIGRATION_1_2)
                .build()


    }

    class MigrationVersion(var previousVersion: Int,
                                 var NextVersion: Int) : Migration(previousVersion, NextVersion) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Since we didn't alter the table, there's nothing else to do here.
            //Action must be done here if schemas are edited
        }

    }


}