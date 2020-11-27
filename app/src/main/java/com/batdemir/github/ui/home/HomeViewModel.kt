package com.batdemir.github.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.batdemir.github.data.entities.RepositoryModel
import com.batdemir.github.data.repository.GithubRepository
import com.batdemir.github.utils.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    fun getRepoListByUser(user: String): LiveData<Resource<List<RepositoryModel>>> {
        return githubRepository.getRepoListByUser(user)
    }
}