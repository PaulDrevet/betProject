package com.example.betproject.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.betproject.R

@Suppress("NAME_SHADOWING")
class PasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedPassword = sharedPreferences.getString("password", null)

        val userPassword: EditText = findViewById(R.id.input_password)
        val userPasswordButton: Button = findViewById(R.id.input_password_button)

        userPasswordButton.setOnClickListener {
            val userPassword = userPassword.text.toString()
            if (userPassword.isEmpty()) {
                Toast.makeText(this, "Please put your password", Toast.LENGTH_SHORT).show()
            }
            else if (savedPassword != userPassword && savedPassword != null){
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show()
                }

                else {
                    saveData()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
        }
    }

    private fun saveData() {
        val insertPassword = findViewById<EditText>(R.id.input_password).text.toString()
        val stayConnect = findViewById<SwitchCompat>(R.id.sw_connect)
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("password", insertPassword)
            putBoolean("stayConnect", stayConnect.isChecked)
        }.apply()
    }


}