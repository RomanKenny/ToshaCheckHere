package com.kovaliov.testapplication.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kovaliov.testapplication.App

object Database {
    //
     fun addCount() {
        if (!checkCountRow())
            App.getInstance().getDatabase().countDao().insert(Count(0, 0))
    }

    private fun checkCountRow(): Boolean {
        return App.getInstance().getDatabase().countDao().isRowIsExist() ?: false
    }

    fun incrementValue(){
        var value = App.getInstance().getDatabase().countDao().getCount()
        Log.d("DEBUG",if (value == null) "null" else value.toString())
        App.getInstance().getDatabase().countDao().insert(Count(value.uid,value.counter++))
    }

}