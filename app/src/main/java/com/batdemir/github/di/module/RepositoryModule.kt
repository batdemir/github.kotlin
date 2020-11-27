package com.batdemir.github.di.module

import com.batdemir.github.data.remote.datasource.GithubRemoteDataSource
import com.batdemir.github.data.repository.GithubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepositoryGithub(
        remoteDataSource: GithubRemoteDataSource
    ) =
        GithubRepository(remoteDataSource)
}