package com.example.betproject.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.betproject.R
import com.example.betproject.databinding.ActivityMainBinding
import com.example.betproject.fragments.MyMoney
import com.example.betproject.fragments.NextBets
import com.example.betproject.fragments.YourBets


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(YourBets())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.myMoney -> replaceFragment(MyMoney())
                R.id.nextBets -> replaceFragment(NextBets())
                R.id.yourBets -> replaceFragment(YourBets())
                else -> {
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}