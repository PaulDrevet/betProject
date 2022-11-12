package com.example.betproject.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.betproject.Model.Bet
import com.example.betproject.Model.Match
import com.example.betproject.R
import com.example.betproject.adapter.BetAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.properties.Delegates

class YourBets : Fragment() {

    private lateinit var betRecyclerView: RecyclerView
    lateinit var adapter : RecyclerView.Adapter<BetAdapter.BetViewHolder>
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private var listMatch = listOf<Match>()
    private var listBet = listOf<Bet>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_your_bets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listBet = readList(requireContext())
        listMatch = readMatchList(requireContext())
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        adapter = BetAdapter(listMatch,listBet)
        betRecyclerView = view.findViewById(R.id.recyclerViewBet)
        betRecyclerView.layoutManager = layoutManager
        betRecyclerView.setHasFixedSize(true)
        betRecyclerView.adapter = adapter

        val saveButton = view.findViewById<Button>(R.id.save_button)
        saveButton?.setOnClickListener(){
            saveStats(it.context)
            adapter = BetAdapter(listMatch,listBet)
            betRecyclerView.adapter = adapter
        }
    }

    private fun readList(parent : Context): List<Bet> {
        val sharedPref = parent.getSharedPreferences("bets", Context.MODE_PRIVATE)
        val jsonString = sharedPref?.getString("bet", "")

        val gson = Gson()
        val type = object : TypeToken<List<Bet>>() {}.type
        if (jsonString != "") {
            var list: List<Bet> = gson.fromJson(jsonString, type)
            return list
        }
        return listOf<Bet>()
    }

    private fun resetList(context: Context) {
        val sharedPref = context.getSharedPreferences("bets", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        listBet = listOf<Bet>()
        val gson = Gson()
        val jsonString = gson.toJson(listBet)

        editor.putString("bet", jsonString).commit()
    }

    private fun readMatchList(context : Context): List<Match> {
        val sharedPref = context.getSharedPreferences("matches", Context.MODE_PRIVATE)
        val jsonString = sharedPref?.getString("key", "")

        val gson = Gson()
        val type = object : TypeToken<List<Match>>() {}.type

        var list: List<Match> = gson.fromJson(jsonString, type)
        return list
    }

    private fun saveStats (context : Context){
        listBet = readList(context)
        val sharedPref = context.getSharedPreferences("stats", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        var nbWin : Int = 0
        var nbBet : Int = listBet.size
        var profit : Int = 0
        var solde : Int = sharedPref.getInt("solde",0)

        listBet.forEach{ bet ->
            if (bet.win == 1){
                nbWin += 1
                profit += bet.win_amount!!
            }
            else {
                profit -= bet.lose_amount!!
            }
        }
        nbWin += sharedPref.getInt("nb_win",0)
        nbBet += sharedPref.getInt("nb_bet",0)
        solde += profit
        profit += sharedPref.getInt("profit",0)
        Log.i("better",nbBet.toString())
        Log.i("better",nbWin.toString())

        editor.putInt("nb_win", nbWin).apply()
        editor.putInt("nb_bet", nbBet).apply()
        editor.putInt("profit", profit).apply()
        editor.putInt("solde", solde).apply()

        val sisi = sharedPref.getInt("nb_win",0)
        Log.i("better",sisi.toString())

        resetList(context)

    }
}