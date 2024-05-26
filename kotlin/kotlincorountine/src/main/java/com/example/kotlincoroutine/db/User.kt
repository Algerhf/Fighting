package com.example.kotlincoroutine.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "first_name",typeAffinity = ColumnInfo.TEXT) var firstName: String,
    @ColumnInfo(name = "last_name",typeAffinity = ColumnInfo.TEXT) var lastName: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid", typeAffinity = ColumnInfo.INTEGER)
    var uid: Int = 0
}