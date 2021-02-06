package com.alian.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.alian.roomdatabase.data.UserDao
import com.alian.roomdatabase.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}