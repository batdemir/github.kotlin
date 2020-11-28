package com.batdemir.github.ui

import android.os.Bundle
import android.view.ViewGroup

interface BaseFragmentActions {
    fun setupDefinition(parent: ViewGroup?, savedInstanceState: Bundle?)
    fun setupData()
    fun setupListener()
}