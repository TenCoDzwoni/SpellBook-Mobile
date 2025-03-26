package com.example.mobilespellbook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment

class SpellListFragment : Fragment() {

    private var listener: OnInteractionListener? = null
    private lateinit var spellListView: ListView
    private lateinit var spellAdapter: ArrayAdapter<String>
    private var spellTitles = mutableListOf<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInteractionListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_spells_list, container, false)

        spellListView = view.findViewById(R.id.spellListView)
        val addButton: Button = view.findViewById(R.id.addSpellButton)

        val mainActivity = activity as? MainActivity
        val spellList = mainActivity?.getSpells() ?: mutableListOf()

        spellTitles = spellList.map { it.title }.toMutableList()
        spellAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, spellTitles)
        spellListView.adapter = spellAdapter

        spellListView.setOnItemClickListener { _, _, position, _ ->
            listener?.onSpellSelected(spellList[position], position)
        }

        addButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddSpellFragment.newInstance(), "AddSpell")
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SpellListFragment()
    }
}
