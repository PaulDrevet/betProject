package com.example.betproject.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.betproject.Model.Bet
import com.example.betproject.Model.Match
import com.example.betproject.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.roundToInt

class MatchAdapter(private var list : List<Match>) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.match_item,
            parent,false
        )
        return MatchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int ) {

        holder.cote1.text = list[position].cote1.toString()
        holder.cote2.text = list[position].cote2.toString()
        holder.cote3.text = list[position].cote3.toString()
        holder.team1.text = list[position].team1
        holder.team2.text = list[position].team2
        holder.date.text = list[position].date
        holder.heure.text = list[position].heure

        holder.cote1.setOnClickListener(){
            val builder = AlertDialog.Builder(it.context)
            val input = EditText(it.context)
            input.hint = "Amount"
            input.inputType = InputType.TYPE_CLASS_NUMBER

            with(builder)
            {
                setView(input)
                setPositiveButton("OK", DialogInterface.OnClickListener{ dialog, _ ->
                    if (input.text.toString() != null) {
                        val amount = Integer.parseInt(input.text.toString())
                        val gain = (list[position].cote1?.times(amount)!!).roundToInt()
                        val win: Int = if (list[position].result == 1) {
                            1
                        } else {
                            0
                        }
                        val bet = Bet(list[position].team1, gain, amount, win, position)
                        saveBet(bet, it)
                        Toast.makeText(it.context,"Bet registered",Toast.LENGTH_SHORT).show()
                    }
                    else{dialog.cancel()}
                })
                setNegativeButton("Cancel",DialogInterface.OnClickListener{ dialog, _ ->  dialog.cancel()})
                show()
            }
        }

        holder.cote2.setOnClickListener(){
            val builder = AlertDialog.Builder(it.context)
            val input = EditText(it.context)
            input.hint = "Amount"
            input.inputType = InputType.TYPE_CLASS_NUMBER

            with(builder)
            {
                setView(input)
                setPositiveButton("OK", DialogInterface.OnClickListener{ dialog, _ ->
                    if (input.text.toString() != "") {
                        val amount = Integer.parseInt(input.text.toString())
                        val gain = (list[position].cote1?.times(amount)!!).roundToInt()
                        val win: Int = if (list[position].result == 2) {
                            1
                        } else {
                            0
                        }
                        val bet = Bet(list[position].team2, gain, amount, win, position)
                        saveBet(bet, it)
                        Toast.makeText(it.context,"Bet registered",Toast.LENGTH_SHORT).show()
                    }
                    else{dialog.cancel()}
                })
                setNegativeButton("Cancel",DialogInterface.OnClickListener{ dialog, _ ->  dialog.cancel()})
                show()
            }
        }

        holder.cote3.setOnClickListener(){
            val builder = AlertDialog.Builder(it.context)
            val input = EditText(it.context)
            input.hint = "Amount"
            input.inputType = InputType.TYPE_CLASS_NUMBER

            with(builder)
            {
                setView(input)
                setPositiveButton("OK", DialogInterface.OnClickListener{ dialog, _ ->
                    if (input.text.toString() != "") {
                        val amount = Integer.parseInt(input.text.toString())
                        val gain = (list[position].cote1?.times(amount)!!).roundToInt()
                        val win: Int = if (list[position].result == 0) {
                            1
                        } else {
                            0
                        }
                        val bet = Bet("NUL", gain, amount, win, position)
                        saveBet(bet, it)
                        Toast.makeText(it.context,"Bet registered",Toast.LENGTH_SHORT).show()
                    }
                    else{dialog.cancel()}
                })
                setNegativeButton("Cancel",DialogInterface.OnClickListener{ dialog, _ ->  dialog.cancel()})
                show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MatchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val date : TextView = itemView.findViewById(R.id.date)
        val heure : TextView = itemView.findViewById(R.id.heure)
        val team1 : TextView = itemView.findViewById(R.id.team1)
        val cote1 : Button = itemView.findViewById(R.id.cote1Button)
        val team2 : TextView = itemView.findViewById(R.id.team2)
        val cote2 : Button = itemView.findViewById(R.id.cote2Button)
        val cote3 : Button = itemView.findViewById(R.id.cote3Button)

    }

    private fun writeBets(list : List<Bet>, it : View){
        val gson = Gson()
        val jsonString = gson.toJson(list)

        val sharedPref = it.context.getSharedPreferences("bets", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("bet",jsonString).apply()

    }

    private fun saveBet(bet : Bet, it : View){

        val sharedPref = it.context.getSharedPreferences("bets", Context.MODE_PRIVATE)
        val jsonString = sharedPref?.getString("bet", "")

        val gson = Gson()
        val type = object : TypeToken<List<Bet>>() {}.type
        if (jsonString!=""){
            var list: List<Bet> = gson.fromJson(jsonString, type)
            val mutablelist : MutableList<Bet> = list.toMutableList()
            mutablelist.add(bet)
            list = mutablelist.toList()
            writeBets(list,it)

        }
        else{
            var list : MutableList<Bet> = mutableListOf<Bet>()
            list.add(bet)
            val immutablelist : List<Bet> = list.toList()
            writeBets(immutablelist, it)
        }
    }


}