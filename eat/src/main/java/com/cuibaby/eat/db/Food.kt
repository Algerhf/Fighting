package com.cuibaby.eat.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "food")
data class Food @Ignore constructor(
    @ColumnInfo(
        name = "name", typeAffinity = ColumnInfo.TEXT
    ) var name: String,
    @ColumnInfo(
        name = "foodType", typeAffinity = ColumnInfo.TEXT
    ) var foodType: String,
) : Serializable, Cloneable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Int = 0

    @ColumnInfo(name = "desc", typeAffinity = ColumnInfo.TEXT)
    var desc: String = ""

    constructor(
        name: String,
        foodType:String,
        desc: String,
    ) : this(name,foodType) {
        this.desc = desc
    }

    override fun equals(other: Any?): Boolean {
        if (other == this) {
            return true
        }

        if (other is Food) {
            return name == other.name && foodType == other.foodType
        }
        return false
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + desc.hashCode()
        return result
    }
}