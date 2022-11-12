package com.example.betproject.fragments

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.betproject.R

class MyMoney : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_my_money, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateStats(requireView(),requireContext())
        val resetButton = view.findViewById<Button>(R.id.reset_button)
        val creditButton = view.findViewById<Button>(R.id.credit_button)

        creditButton.setOnClickListener(){
            creditMoney(requireView(),requireContext())
        }

        resetButton.setOnClickListener(){
            resetStats(requireView(),requireContext())
        }

    }

    private fun updateStats(view: View, context: Context){
        val sharedPref = context.getSharedPreferences("stats", Context.MODE_PRIVATE)
        refeshPage(view,context)
    }

    private fun resetStats(view: View, context: Context){

        val sharedPref = context.getSharedPreferences("stats", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("profit", 0).apply()
        editor.putInt("solde", 1000).apply()
        editor.putInt("nb_bet", 0).apply()
        editor.putInt("nb_win", 0).apply()

        refeshPage(view,context)
    }

    private fun creditMoney(view: View, context: Context){

        val sharedPref = context.getSharedPreferences("stats", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        var solde = sharedPref.getInt("solde",0)
        solde += 100
        editor.putInt("solde", solde).apply()
        refeshPage(view,context)
    }

    private fun refeshPage(view: View, context: Context){
        val sharedPref = context.getSharedPreferences("stats", Context.MODE_PRIVATE)

        val solde = sharedPref.getInt("solde",0)
        val nbWin = sharedPref.getInt("nb_win",0)
        val nbBet = sharedPref.getInt("nb_bet",0)
        val profit = sharedPref.getInt("profit",0)
        val winRate : Float = (nbWin.toFloat() / (nbBet.toFloat()))*100

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        val progressBarText = view.findViewById<TextView>(R.id.text_view_progress)
        val soldeText = view.findViewById<TextView>(R.id.sold_textView)
        val profitText = view.findViewById<TextView>(R.id.profit_textView)

        progressBar.progress = winRate.toInt()
        progressBarText.text = "${winRate.toInt()}%"
        soldeText.text = "${solde}€"
        profitText.text = "${profit}€"

    }

}