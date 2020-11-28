package com.batdemir.github.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.batdemir.github.R
import com.batdemir.github.app.MyApplication
import com.batdemir.github.databinding.ActivityMainBinding
import com.batdemir.github.di.component.HomeComponent
import com.batdemir.github.utils.setupWithNavController

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
        }
    }

    private var currentNavController: LiveData<NavController>? = null

    var homeComponent: HomeComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val applicationComponent = (application as MyApplication)
            .applicationComponent
        applicationComponent.inject(this)
        homeComponent = applicationComponent.homeComponent().create()
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            setupBottomNavigationBar()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.home_navigation
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = binding.bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navigation_host_fragment,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(
            this,
            { navController -> setupActionBarWithNavController(navController) },
        )
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}