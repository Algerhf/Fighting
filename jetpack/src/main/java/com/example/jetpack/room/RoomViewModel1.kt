package com.example.jetpack.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class RoomViewModel1 : ViewModel() {

    private val studentRepository by lazy {
        StudentRepository()
    }

    fun insert(vararg student: Student) {
        studentRepository.insert(*student)
    }

    fun delete(vararg student: Student) {
        studentRepository.delete(*student)
    }

    fun update(vararg student: Student) {
        studentRepository.update(*student)
    }

    fun deleteAll() {
        studentRepository.deleteAll()
    }

    fun queryAll(): LiveData<List<Student>> {
        return studentRepository.queryAll()
    }

}