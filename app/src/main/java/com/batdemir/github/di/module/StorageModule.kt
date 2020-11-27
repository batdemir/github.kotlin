package com.batdemir.github.di.module

import com.batdemir.github.data.storage.SharedPreferencesStorage
import com.batdemir.github.data.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}