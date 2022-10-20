package com.example.betproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.betproject.Models.User
import com.example.betproject.R


class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val userList = ArrayList<User>()
    private var firstnames = arrayOf("test")
    private var lastnames = arrayOf("eada")
    private var age = arrayOf("tdadada")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        userList.add(User("bonjour","enrevoir","14"))
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_item,
            parent,false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //val currentitem:User = userList[position]

        holder.firstName.text = firstnames[position]
        holder.lastName.text = lastnames[position]
        holder.age.text = age[position]

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList : List<User>){
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val firstName : TextView = itemView.findViewById(R.id.tvfirstName)
        val lastName : TextView = itemView.findViewById(R.id.tvlastName)
        val age : TextView = itemView.findViewById(R.id.tvage)
    }
}