package com.example.betproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
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