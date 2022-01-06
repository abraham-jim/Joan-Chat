package com.hfad.joanchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Make the toolbar like the app's default app bar
         val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
         setSupportActionBar(toolbar)
        /**
         * Configure the toolbar to include an up button and
         * display which screen you've navigated to
         * */
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)
        //Link the bottom navigation bar to the navigation controller.
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavView.setupWithNavController(navController)
    }

    // Add menu items to the toolbar (in this case the Help item)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    // Navigate to a destination when an item is clicked.
    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
}

/**
 *      *** When the app runs.***
 * 1) The app is launche and MainActicity gets created.
 *    -> InboxFragment is added to the navigation host and displayed on the device screen.
 * 2) ManinActivity's onCreateOptionsMenu method runs.
 *    -> It adds the Help menu item defined in menu_toolbar.xml to the toolbar.
 * 3) The user clicks the Help item in the toolbar.
 * 4) MainActivity's onOptionItemSelected() method runs.
 *    -> It passes navigation for the Help item to the navigation controller.
 * 5) The navigation controller looks for the Help item's ID in navigation graph.
 * 6) The navigation controller replaces InboxFragment with HelpFragment in the navigation host.
 * */