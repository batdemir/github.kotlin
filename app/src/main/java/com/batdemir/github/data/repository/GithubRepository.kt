package com.batdemir.github.data.repository

import com.batdemir.github.data.entities.RepositoryModel
import com.batdemir.github.data.local.datasource.GithubLocalDataSource
import com.batdemir.github.data.remote.datasource.GithubRemoteDataSource
import com.batdemir.github.utils.performGetOperation
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val remoteDataSource: GithubRemoteDataSource,
    private val localDataSource: GithubLocalDataSource
) {
    fun getRepoListByUser(user: String) = performGetOperation(
        databaseQuery = { localDataSource.getRepoListByUser(user) },
        networkCall = { remoteDataSource.getRepoListByUser(user) },
        saveCallResult = {
            for (item in it) {
                item.ownerName = user
                localDataSource.add(item)
            }
        }
    )

    suspend fun updateRepository(model: RepositoryModel) = localDataSource.update(model)
}