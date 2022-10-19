package com.example.betproject.activitys

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.betproject.R

class UsernameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        //supportActionBar?.hide()
        setContentView(R.layout.activity_main)

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