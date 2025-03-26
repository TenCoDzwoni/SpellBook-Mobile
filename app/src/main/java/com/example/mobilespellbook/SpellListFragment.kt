package com.example.mobilespellbook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class SpellListFragment : Fragment() {

    private lateinit var listView: ListView
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
        val view = inflater.inflate(R.layout.fragment_spells_list, container, false)

        listView = view.findViewById(R.id.spellListView)
        val addButton = view.findViewById<View>(R.id.addSpellButton)

        val spells = (activity as MainActivity).getSpells()
        val adapter = SpellListAdapter(requireContext(), spells)
        listView.adapter = adapter
//                                                                       parent, view, position, id
        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, position, _ ->
            showDeleteDialog(position)
            true
        }

        addButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddSpellFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    private fun showDeleteDialog(position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Usuń zaklęcie")
            .setMessage("Czy na pewno chcesz usunąć to zaklęcie?")
            .setPositiveButton("Tak") { _, _ ->
                listener?.onSpellDeleted(position)
            }
            .setNegativeButton("Anuluj", null)
            .show()
    }

    fun refreshList() {
        val spells = (activity as MainActivity).getSpells()
        val adapter = SpellListAdapter(requireContext(), spells)
        listView.adapter = adapter
    }
}