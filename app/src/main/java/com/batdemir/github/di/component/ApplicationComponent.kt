package com.batdemir.github.di.component

import android.content.Context
import com.batdemir.github.di.module.LocalModule
import com.batdemir.github.di.module.NetworkModule
import com.batdemir.github.di.module.RepositoryModule
import com.batdemir.github.di.module.StorageModule
import com.batdemir.github.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        LocalModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        StorageModule::class
    ]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(mainActivity: MainActivity)
    fun homeComponent(): HomeComponent.Factory
}