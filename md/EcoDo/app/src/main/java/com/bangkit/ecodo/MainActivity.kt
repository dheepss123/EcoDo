package com.bangkit.ecodo

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bangkit.ecodo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
        val navController = (fragment as NavHostFragment).navController

//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_trash, R.id.navigation_settings
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)

        supportActionBar?.hide()

        navView.setupWithNavController(navController)
    }
}