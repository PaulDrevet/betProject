package com.example.betproject.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.betproject.Model.Match
import com.example.betproject.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import java.util.*
import kotlin.concurrent.schedule


class UsernameActivity : AppCompatActivity() {

    var matchList = arrayListOf<Match>()
    private val database = Firebase.database("https://betproject-9b85a-default-rtdb.europe-west1.firebasedatabase.app/")
    private val myRef = database.getReference("matches")

    private val matchListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val matchListSnapshot = dataSnapshot.children
            matchListSnapshot.forEach {
                matchList.add(it.getValue<Match>()!!)

            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.i("firebase", "non")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        loadUserData()
        myRef.addValueEventListener(matchListener)
        Timer().schedule(3000){
            writeList(matchList)
        }

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
                saveUserData()
                val intent = Intent(applicationContext, PasswordActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun writeList(list : List<Match>){
        var list = list.sortedBy { it.heure }
        list = list.sortedBy { it.date }
        val gson = Gson()
        val jsonString = gson.toJson(list)

        val sharedPref = getSharedPreferences("matches", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("key",jsonString).commit()

    }


    private fun saveUserData(){
        val insertName = findViewById<EditText>(R.id.input_name).text.toString()
        val sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply{
            putString("username",insertName)
        }.apply()
    }

    private fun loadUserData(){
        val sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
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