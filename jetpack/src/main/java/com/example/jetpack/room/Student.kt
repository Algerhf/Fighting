package com.example.jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "student")
class Student @Ignore constructor(
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT) var name: String,
    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER) var age: Int
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Int = 0

    @Ignore
    constructor() : this("", 0)

    @Ignore
    constructor(name: String) : this(name, 0)

    constructor(id: Int, name: String, age: Int) : this(name, age) {
        this.id = id
    }

    override fun toString(): String {
        return "Student(name='$name', age=$age, id=$id)"
    }
}
