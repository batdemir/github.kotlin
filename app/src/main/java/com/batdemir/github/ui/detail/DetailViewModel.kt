package com.batdemir.github.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdemir.github.data.entities.RepositoryModel
import com.batdemir.github.data.repository.GithubRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _item = MutableLiveData<RepositoryModel>()

    val item: LiveData<RepositoryModel> = _item

    fun start(repositoryModel: RepositoryModel) {
        _item.value = repositoryModel
    }

    fun updateModel(): Boolean {
        val status = !_item.value!!.isFavorite
        _item.value!!.isFavorite = status
        viewModelScope.launch {
            githubRepository.updateRepository(_item.value!!)
        }
        return status
    }
}