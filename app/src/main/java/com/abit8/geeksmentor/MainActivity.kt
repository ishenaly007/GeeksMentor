package com.abit8.geeksmentor

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.abit8.geeksmentor.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*supportFragmentManager.beginTransaction().add(R.id.fragment_container, OnBoardFragment1()).commit()

        // Создаем ViewPager2 и адаптер для фрагментов
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Фрагменты для онбординга
        class OnboardingFragment1 : Fragment(R.layout.fragment_on_board1)
        class OnboardingFragment2 : Fragment(R.layout.fragment_on_board2)
        class OnboardingFragment3 : Fragment(R.layout.fragment_on_board3)

        // Добавляем три фрагмента в адаптер
        val fragment1 = OnboardingFragment1()
        val fragment2 = OnboardingFragment2()
        val fragment3 = OnboardingFragment3()
        adapter.addFragment(fragment1)
        adapter.addFragment(fragment2)
        adapter.addFragment(fragment3)*/

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //SMS verification
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
    }
}
