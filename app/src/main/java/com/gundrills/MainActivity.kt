package com.gundrills


import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.get


import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView



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

        val navhostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navhostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.timerFragment,R.id.statsFragment,R.id.studyFragment,R.id.referencesFragment))
        setupActionBarWithNavController(navController)//need this one and line below so labels work properly on toolbar
        toolbar.setupWithNavController(navController,appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

    }



}