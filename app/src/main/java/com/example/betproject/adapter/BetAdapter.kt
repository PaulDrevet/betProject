package com.example.betproject.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.betproject.Model.Bet
import com.example.betproject.Model.Match
import com.example.betproject.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.roundToInt

class BetAdapter (private var listMatch : List<Match>, private var listBet : List<Bet> ) : RecyclerView.Adapter<BetAdapter.BetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.match_bet_item,
            parent,false
        )
        return BetViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: BetViewHolder, position: Int ) {

        holder.teambet.text = listBet[position].team
        holder.bet_win_amount.text = listBet[position].win_amount.toString()
        holder.bet_loose_amount.text = listBet[position].lose_amount.toString()
        holder.bet_team1.text = listMatch[listBet[position].id!!].team1
        holder.bet_team2.text = listMatch[listBet[position].id!!].team2
        holder.bet_date.text = listMatch[listBet[position].id!!].date

        holder.delete.setOnClickListener(){
            deleteBet(position, it)
            Toast.makeText(it.context,"Bet deleted",Toast.LENGTH_SHORT).show()
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return listBet.size
    }

    class BetViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val bet_date : TextView = itemView.findViewById(R.id.bet_date)
        val bet_team1 : TextView = itemView.findViewById(R.id.bet_team1)
        val bet_team2 : TextView = itemView.findViewById(R.id.bet_team2)
        val teambet : TextView = itemView.findViewById(R.id.bet_teambet)
        val bet_loose_amount : TextView = itemView.findViewById(R.id.bet_amount_loose)
        val bet_win_amount : TextView = itemView.findViewById(R.id.bet_amount_win)
        val delete : ImageButton = itemView.findViewById(R.id.deleteButton)

    }

    private fun deleteBet(position: Int, it : View){
        var mutableListBet: MutableList<Bet> = listBet.toMutableList()
        mutableListBet.removeAt(position)
        listBet = mutableListBet.toList()

        val sharedPref = it.context.getSharedPreferences("bets", Context.MODE_PRIVATE)

        val gson = Gson()
        val jsonString = gson.toJson(listBet)
        val type = object : TypeToken<List<Bet>>() {}.type
        val editor = sharedPref.edit()
        editor.putString("bet",jsonString).apply()

    }
}