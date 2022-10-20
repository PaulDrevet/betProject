package com.example.betproject.activitys

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.betproject.fragments.MyMoney
import com.example.betproject.fragments.NextBets
import com.example.betproject.R
import com.example.betproject.fragments.YourBets
import com.example.betproject.databinding.ActivityAllBetsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AllBetsActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null

    private lateinit var binding : ActivityAllBetsBinding
    val db = Firebase.firestore
    val user = hashMapOf(
        "first" to "Ada"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_all_bets)
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
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}