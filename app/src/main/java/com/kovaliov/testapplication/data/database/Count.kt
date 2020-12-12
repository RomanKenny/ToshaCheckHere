package com.kovaliov.testapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Count(
    @PrimaryKey (autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "counter", defaultValue = "0" ) var counter: Int
) {

}
