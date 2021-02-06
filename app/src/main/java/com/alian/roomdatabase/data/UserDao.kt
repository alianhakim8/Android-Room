package com.alian.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alian.roomdatabase.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user ORDER BY id asc")
     fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user:User)

}