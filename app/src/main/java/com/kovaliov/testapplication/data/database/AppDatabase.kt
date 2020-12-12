package com.kovaliov.testapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Count::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countDao(): CountDao
}