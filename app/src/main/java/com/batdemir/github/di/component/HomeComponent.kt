package com.batdemir.github.di.component

import com.batdemir.github.ui.detail.DetailFragment
import com.batdemir.github.ui.home.HomeFragment
import dagger.Subcomponent

@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)
    fun inject(detailFragment: DetailFragment)
}
