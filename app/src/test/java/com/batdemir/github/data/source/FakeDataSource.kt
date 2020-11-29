package com.batdemir.github.data.source

import com.batdemir.github.data.entities.RepositoryModel
import com.batdemir.github.utils.Resource

class FakeDataSource(var repos: MutableList<RepositoryModel>? = mutableListOf()) {
    suspend fun getRepoListByUser(user: String): Resource<List<RepositoryModel>> {
        repos?.let {
            return Resource.success(it)
        }
        return Resource.error("Repos not found", null)
    }
}