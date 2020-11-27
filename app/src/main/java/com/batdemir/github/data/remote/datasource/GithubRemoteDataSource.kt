package com.batdemir.github.data.remote.datasource

import com.batdemir.github.data.remote.BaseDataSource
import com.batdemir.github.data.remote.service.GithubService
import javax.inject.Inject

class GithubRemoteDataSource @Inject constructor(
    private val service: GithubService
) : BaseDataSource() {
    suspend fun getRepoListByUser(user: String) = getResult { service.getRepoListByUser(user) }
}