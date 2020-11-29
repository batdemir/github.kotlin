package com.batdemir.github.data.local.datasource

import com.batdemir.github.data.entities.RepositoryModel
import com.batdemir.github.data.local.dao.GithubDao
import javax.inject.Inject

class GithubLocalDataSource @Inject constructor(
    private val dao: GithubDao
) {
    fun getRepoListByUser(user: String) = dao.get(user)

    suspend fun add(model: RepositoryModel) = dao.add(model)

    suspend fun update(model: RepositoryModel) = dao.update(model)
}