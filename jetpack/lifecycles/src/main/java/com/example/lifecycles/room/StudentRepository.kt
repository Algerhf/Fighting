package com.example.lifecycles.room

import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

/**
 *
 * @author hufan
 * 创建日期：2023/4/14 17:46
 * 描述：
 *
 */
class StudentRepository {

    fun insert(vararg student: Student) {
        Executors.newCachedThreadPool().submit {
            DatabaseManager.instance.mDb.mStudentDao.insert(*student)
        }
    }

    fun delete(vararg student: Student) {
        Executors.newCachedThreadPool().submit {
            DatabaseManager.instance.mDb.mStudentDao.delete(*student)
        }

    }

    fun update(vararg student: Student) {
        Executors.newCachedThreadPool().submit {
            DatabaseManager.instance.mDb.mStudentDao.update(*student)
        }
    }

    fun deleteAll() {
        Executors.newCachedThreadPool().submit {
            DatabaseManager.instance.mDb.mStudentDao.deleteAll()
        }
    }

    fun queryAll(): LiveData<List<Student>> {
        return DatabaseManager.instance.mDb.mStudentDao.queryAll()
    }
}