package com.example.betproject.activitys

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.betproject.R
import com.example.betproject.repository.UserRepository
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.prefs.Preferences

class UsernameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)

        val userName:EditText = findViewById(R.id.input_name)
        val userNameButton: Button = findViewById(R.id.input_name_button)

        userNameButton.setOnClickListener{
            val userNameStr = userName.text.toString()
            if (userNameStr.isEmpty()){
                Toast.makeText(this,"Please put your name",Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(applicationContext, PasswordActivity::class.java)
                startActivity(intent)
            }
        }
    }

}