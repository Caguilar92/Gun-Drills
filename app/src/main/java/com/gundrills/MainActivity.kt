package com.gundrills


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

/*
*This activity is the entry point of the application when initially launched
 */
class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            toolbar = findViewById(R.id.topAppBar)
            setSupportActionBar(toolbar)


       bottomNavigationView = findViewById(R.id.bottomNavigationView)


        //NavHostFragment is the layout container where fragment navigation will occur. NaveHostFragment has a NavContrller that defined that navigation within
        //the NavHostFragment. The navigation graph and menu this NavHostFragment will use defined in the fragment defined in this activities associated layout file
        val navhostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment

        //Navigation Controller manages navigation within a NavHostFragment. The navigation flow is determined by the navigation graph, and the navcontoller
        //manages the navigation of destination.
        val navController = navhostFragment.findNavController()

        //Add top level destination in order for NavigationUI to correctly implement the fragment labels and up button on the toolbar
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.timerFragment,R.id.statsFragment,R.id.studyFragment,R.id.referencesFragment))

        //tool bar titles will automatically be updated
        setupActionBarWithNavController(navController)
        //shows navigation up button on any destination that is not a top level destination and declared in the AppBarConfiguration
        toolbar.setupWithNavController(navController,appBarConfiguration)

        //sets up bottom navigation in order for destination navigation to be triggered upon menu click
        bottomNavigationView.setupWithNavController(navController)

    }



}