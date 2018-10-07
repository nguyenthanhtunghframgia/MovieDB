package com.example.nguyenthanhtungh.moviedb.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nguyenthanhtungh.moviedb.data.local.dao.MovieDao
import com.example.nguyenthanhtungh.moviedb.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}