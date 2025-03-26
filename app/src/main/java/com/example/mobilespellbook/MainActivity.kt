package com.example.mobilespellbook

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), OnInteractionListener {

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()
    private var spells = mutableListOf<Spell>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("spellbook_prefs", Context.MODE_PRIVATE)
        loadSpells()

        if (savedInstanceState == null) {
            openSpellListFragment()
        }
    }

    private fun openSpellListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SpellListFragment())
            .commit()
    }

    override fun onSpellSelected(spell: Spell, index: Int) {
        // Implementacja szczegółów zaklęcia
    }

    override fun onSpellAdded(newSpell: Spell) {
        spells.add(newSpell)
        saveSpells()
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? SpellListFragment)?.refreshList()
        supportFragmentManager.popBackStack()
    }

    override fun onSpellDeleted(index: Int) {
        if (index in spells.indices) {
            spells.removeAt(index)
            saveSpells()
            (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? SpellListFragment)?.refreshList()
        }
    }

    private fun loadSpells() {
        val json = sharedPreferences.getString("spells", null)
        spells = gson.fromJson(json, object : TypeToken<MutableList<Spell>>() {}.type) ?: mutableListOf()
    }

    private fun saveSpells() {
        sharedPreferences.edit()
            .putString("spells", gson.toJson(spells))
            .apply()
    }

    fun getSpells() = spells
}