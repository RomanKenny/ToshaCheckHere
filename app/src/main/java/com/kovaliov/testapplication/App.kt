package com.kovaliov.testapplication

import android.app.Application
import androidx.room.Room
import com.kovaliov.testapplication.data.database.AppDatabase
import com.kovaliov.testapplication.data.database.Database
import com.kovaliov.testapplication.di.ApplicationComponent
import com.kovaliov.testapplication.di.DaggerApplicationComponent


class App : Application() {


    private lateinit var database: AppDatabase

    fun getDatabase(): AppDatabase {
        return database
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "Count"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
        Database.addCount()
        applicationComponent = DaggerApplicationComponent.create()


    }

    companion object {
        private lateinit var instance: App
        private lateinit var applicationComponent: ApplicationComponent

        fun getAppComponent() = applicationComponent
        fun getInstance(): App {
            return instance
        }
    }
}