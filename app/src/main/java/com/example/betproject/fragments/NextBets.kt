package com.example.betproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.betproject.Adapter.MyAdapter
import com.example.betproject.Models.User
import com.example.betproject.Models.UserViewModel
import com.example.betproject.R

class NextBets : Fragment() {

    private lateinit var viewModel : UserViewModel
    private lateinit var userRecyclerView: RecyclerView
    lateinit var adapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>
    private lateinit var layoutManager : RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_bets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(context)
        adapter = MyAdapter()
        userRecyclerView = view.findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = layoutManager
        userRecyclerView.setHasFixedSize(true)
        userRecyclerView.adapter = adapter
    }

}