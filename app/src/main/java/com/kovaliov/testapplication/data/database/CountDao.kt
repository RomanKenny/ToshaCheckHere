package com.kovaliov.testapplication.data.database

import androidx.room.*

@Dao
interface CountDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(count: Count)

    @Delete
    fun delete(count: Count)

    @Query("SELECT * FROM Count")
    fun getAll(): List<Count>

    @Query("SELECT counter FROM Count WHERE uid = 0")
    fun getValue(): Int

    @Query("SELECT * FROM Count WHERE uid = 0")
    fun getCount(): Count

    @Query("UPDATE Count SET Counter = counter+1 WHERE uid = 0")
    fun incrementValue()

    @Query("SELECT EXISTS(SELECT * FROM Count WHERE uid = 0)")
    fun isRowIsExist() : Boolean
}