package com.batdemir.github.data.remote.service

import com.batdemir.github.data.entities.RepositoryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    suspend fun getRepoListByUser(@Path("user") user: String): Response<List<RepositoryModel>>
}