package com.example.betproject.repository

import androidx.lifecycle.MutableLiveData
import com.example.betproject.models.User
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepository {
    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance("https://betproject-9b85a-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users")

    @Volatile private var INSTANCE : UserRepository ?= null

    fun getInstance() : UserRepository{
        return INSTANCE ?: synchronized(this){
            val instance = UserRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadUsers (userList : MutableLiveData<List<User>>){

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {

                    val _userList : List<User> = snapshot.children.map {dataSnapshot ->
                        dataSnapshot.getValue(User::class.java)!!
                    }
                    userList.postValue(_userList)
                }
                catch (e : Exception){}
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}