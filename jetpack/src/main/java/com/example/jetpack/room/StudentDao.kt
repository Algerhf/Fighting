package com.example.jetpack.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 *
 * @author hufan
 * 创建日期：2023/4/14 17:49
 * 描述：
 *
 */
@Dao
interface StudentDao {

    @Insert(entity = Student::class)
    fun insert(vararg students: Student)

    @Delete(entity = Student::class)
    fun delete(vararg students: Student)

    @Update(entity = Student::class)
    fun update(vararg students: Student)

    @Query("DELETE FROM student")
    fun deleteAll()

    @Query("SELECT * FROM student")
    fun queryAll():LiveData<List<Student>>
}