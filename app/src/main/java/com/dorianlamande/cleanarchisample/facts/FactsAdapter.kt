package com.dorianlamande.cleanarchisample.facts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dorianlamande.cleanarchisample.R
import com.dorianlamande.cleanarchisample.facts.FactsAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_fact.view.*

class FactsAdapter(private val factsViewModel: List<FactViewModel>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =
            factsViewModel.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fact.text = factsViewModel[position].fact
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fact: TextView = view.factTextView
    }
}