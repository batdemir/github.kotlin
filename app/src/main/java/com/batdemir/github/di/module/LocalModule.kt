package com.batdemir.github.di.module

import android.content.Context
import com.batdemir.github.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideDaoGithub(db: AppDatabase) = db.githubDao()
}