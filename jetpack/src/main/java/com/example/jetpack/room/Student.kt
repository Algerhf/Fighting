package com.example.jetpack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(@PrimaryKey var id: Int, var name: String, var age: Int)
