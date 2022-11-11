package com.example.betproject.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.betproject.R
import com.example.betproject.databinding.ActivityMainBinding
import com.example.betproject.fragments.MyMoney
import com.example.betproject.fragments.NextBets
import com.example.betproject.fragments.YourBets
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {


    lateinit var imageView: ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


    fun quitApp(view: View) {
        this@MainActivity.finishAffinity()
        exitProcess(0)
    }

}