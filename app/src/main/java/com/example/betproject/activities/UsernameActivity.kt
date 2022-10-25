package com.example.betproject.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.betproject.R

class UsernameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        loadData()

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
                saveData()
                val intent = Intent(applicationContext, PasswordActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun saveData(){
        val insertName = findViewById<EditText>(R.id.input_name).text.toString()
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply{
            putString("username",insertName)
        }.apply()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("username", null)
        val stayConnect = sharedPreferences.getBoolean("stayConnect", false)
        if (stayConnect){
            Toast.makeText(this, "Welcome back $savedName",Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        if (!stayConnect && savedName != null){
            val intent = Intent(applicationContext, PasswordActivity::class.java)
            startActivity(intent)
        }
    }

}