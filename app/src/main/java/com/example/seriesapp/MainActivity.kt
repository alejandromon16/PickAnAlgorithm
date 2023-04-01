package com.example.seriesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.seriesapp.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.item_home -> replaceFragment(HomeFragment())
                R.id.item_history -> replaceFragment(HistoryFragment())
                R.id.item_new_algorithm -> replaceFragment(NewAlgorithmFragment())

                else -> {
                    replaceFragment(HomeFragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}