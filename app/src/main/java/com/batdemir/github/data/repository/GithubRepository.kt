package com.batdemir.github.data.repository

import com.batdemir.github.data.remote.datasource.GithubRemoteDataSource
import com.batdemir.github.utils.performGetOperation
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val remoteRemoteDataSource: GithubRemoteDataSource
) {
    fun getRepoListByUser(user: String) = performGetOperation(
        networkCall = { remoteRemoteDataSource.getRepoListByUser(user) }
    )
}