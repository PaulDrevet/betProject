package com.example.betproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)


        val userPassword: EditText = findViewById(R.id.input_password)
        val userPasswordButton: Button = findViewById(R.id.input_password_button)

        userPasswordButton.setOnClickListener {
            val userName = userPassword.text.toString()
            if (userName.isEmpty()) {
                Toast.makeText(this, "Please put your password", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(applicationContext, AllBetsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}