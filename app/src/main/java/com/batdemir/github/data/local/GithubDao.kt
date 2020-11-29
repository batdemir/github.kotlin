package com.batdemir.github.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.batdemir.github.data.entities.RepositoryModel

@Dao
interface GithubDao {
    @Query("SELECT * FROM Repository WHERE ownerName = :user")
    fun get(user: String): LiveData<List<RepositoryModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(model: RepositoryModel)

    @Delete
    suspend fun delete(model: RepositoryModel)

    @Update
    suspend fun update(model: RepositoryModel)
}