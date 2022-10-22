package com.example.betproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.betproject.R

//7 gr-2-7
//7SD
class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var team1 = arrayOf("test","dadad")
    private var cote1 = arrayOf("1.60","1.90")
    private var team2 = arrayOf("eada","ddda")
    private var cote2 = arrayOf("1.50","1.40")
    private var date = arrayOf("ea","da")
    private var heure = arrayOf("ea","da")
    private var cote3 = arrayOf("1.00","1.30")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.match_item,
            parent,false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.cote1.text = cote1[position]
        holder.cote2.text = cote2[position]
        holder.cote3.text = cote3[position]
        holder.team1.text = team1[position]
        holder.team2.text = team2[position]
        holder.date.text = date[position]
        holder.heure.text = heure[position]
    }

    override fun getItemCount(): Int {
        return team2.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.date)
        val heure : TextView = itemView.findViewById(R.id.heure)
        val team1 : TextView = itemView.findViewById(R.id.team1)
        val cote1 : Button = itemView.findViewById(R.id.cote1Button)
        val team2 : TextView = itemView.findViewById(R.id.team2)
        val cote2 : Button = itemView.findViewById(R.id.cote2Button)
        val cote3 : Button = itemView.findViewById(R.id.cote3Button)

    }
}