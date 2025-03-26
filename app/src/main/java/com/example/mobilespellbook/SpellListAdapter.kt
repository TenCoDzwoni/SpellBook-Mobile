package com.example.mobilespellbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView

class SpellListAdapter(
    context: Context,
    private val spells: List<Spell>
) : ArrayAdapter<Spell>(context, 0, spells) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_spell, parent, false)

        val spell = getItem(position) ?: return view

        view.findViewById<TextView>(R.id.spellName).text = spell.name
        view.findViewById<TextView>(R.id.spellCost).text = "Koszt: ${spell.cost}"
        view.findViewById<TextView>(R.id.spellFunction).text = "Efekt: ${spell.function}"
        view.findViewById<RatingBar>(R.id.spellRating).rating = spell.rating

        return view
    }
}