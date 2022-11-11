package com.example.betproject.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.betproject.Model.Match
import com.example.betproject.adapter.MatchAdapter
import com.example.betproject.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NextBets : Fragment() {

    private lateinit var matchRecyclerView: RecyclerView
    lateinit var adapter: RecyclerView.Adapter<MatchAdapter.MatchViewHolder>
    private lateinit var layoutManager : RecyclerView.LayoutManager
    private var list = listOf<Match>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_bets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list = readList(requireContext())
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        adapter = MatchAdapter(list)
        matchRecyclerView = view.findViewById(R.id.recyclerViewMatch)
        matchRecyclerView.layoutManager = layoutManager
        matchRecyclerView.setHasFixedSize(true)
        matchRecyclerView.adapter = adapter
    }
    private fun readList(parent : Context): List<Match> {
        val sharedPref = parent.getSharedPreferences("matches", Context.MODE_PRIVATE)
        val jsonString = sharedPref?.getString("key", "")

        val gson = Gson()
        val type = object : TypeToken<List<Match>>() {}.type

        var list: List<Match> = gson.fromJson(jsonString, type)
        return list
    }
}