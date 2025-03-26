package com.example.mobilespellbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SpellListAdapter(
    private val spellList: List<Spell>,
    private val onSpellClick: (Spell, Int) -> Unit
) : RecyclerView.Adapter<SpellListAdapter.SpellViewHolder>() {

    inner class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val spellTitle: TextView = itemView.findViewById(R.id.spellName)
        val spellRatingBar: RatingBar = itemView.findViewById(R.id.spellRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spell_list, parent, false)
        return SpellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val currentSpell = spellList[position]
        holder.spellTitle.text = currentSpell.name
        holder.spellRatingBar.rating = currentSpell.rating

        holder.itemView.setOnClickListener {
            onSpellClick(currentSpell, position)
        }
    }

    override fun getItemCount(): Int = spellList.size
}
