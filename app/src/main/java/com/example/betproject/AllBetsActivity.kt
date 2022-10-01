package com.example.betproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.betproject.databinding.ActivityAllBetsBinding

class AllBetsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAllBetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBetsBinding.inflate(layoutInflater)
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

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()

    }
}