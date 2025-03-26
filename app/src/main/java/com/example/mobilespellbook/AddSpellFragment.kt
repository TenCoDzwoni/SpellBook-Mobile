package com.example.mobilespellbook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.fragment.app.Fragment

class AddSpellFragment : Fragment() {

    private var listener: OnInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInteractionListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_spell, container, false)

        val nameEditText = view.findViewById<EditText>(R.id.spellNameEditText)
        val costEditText = view.findViewById<EditText>(R.id.spellCostEditText)
        val functionEditText = view.findViewById<EditText>(R.id.spellFuncionEditText)
        val ratingBar = view.findViewById<RatingBar>(R.id.spellRatingBar)

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            val newSpell = Spell(
                name = nameEditText.text.toString(),
                cost = costEditText.text.toString(),
                function = functionEditText.text.toString(),
                rating = ratingBar.rating
            )
            listener?.onSpellAdded(newSpell)
        }

        return view
    }
}