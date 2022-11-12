package com.example.betproject.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.Image
import android.net.Uri
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

        holder.delete.setOnClickListener(){
            deleteBet(position, it)
            Toast.makeText(it.context,"Bet deleted",Toast.LENGTH_SHORT).show()
            notifyDataSetChanged()
        }

        holder.share.setOnClickListener(){
            shareBet(position, it)
            notifyDataSetChanged()
        }


    }

    override fun getItemCount(): Int {
        return listBet.size
    }

    class BetViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val bet_team1 : TextView = itemView.findViewById(R.id.bet_team1)
        val bet_team2 : TextView = itemView.findViewById(R.id.bet_team2)
        val teambet : TextView = itemView.findViewById(R.id.bet_teambet)
        val bet_loose_amount : TextView = itemView.findViewById(R.id.bet_amount_loose)
        val bet_win_amount : TextView = itemView.findViewById(R.id.bet_amount_win)
        val delete : ImageButton = itemView.findViewById(R.id.deleteButton)
        val share : ImageButton = itemView.findViewById(R.id.share)

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

        val uri = Uri.parse("smsto:12346556")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", "hdahda")
        it.context.startActivity(intent)
    }
    private fun shareBet(position: Int, it : View){

        val team1 = listMatch[listBet[position].id!!].team1
        val team2 = listMatch[listBet[position].id!!].team2
        val pari = listBet[position].lose_amount
        val teamParie = listBet[position].team
        val s = "J'ai parié ${pari}€ sur $teamParie dans le match $team1 vs $team2 "

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, s)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Voila mon pari pour ce match: ")
        it.context.startActivity(Intent.createChooser(shareIntent, "Share bet via :"))

    }
}