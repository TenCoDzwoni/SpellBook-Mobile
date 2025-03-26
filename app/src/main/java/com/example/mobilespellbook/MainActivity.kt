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

        sharedPreferences = getSharedPreferences("my_cookbook_prefs", Context.MODE_PRIVATE)
        loadSpells()

        if (savedInstanceState == null) {
            openRecipeListFragment()
        }
    }

    private fun openRecipeListFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, SpellListFragment.newInstance(), "SpellList")
        }
    }

    override fun onSpellAdded(spell: Spell) {
        spells.add(spell)
        saveRecipes()
        supportFragmentManager.popBackStack()
    }

    override fun onSpellDeleted(position: Int) {
        if (position in spells.indices) {
            spells.removeAt(position)
            saveRecipes()
        }
        supportFragmentManager.popBackStack()
    }

    private fun loadSpells() {
        val json = sharedPreferences.getString("spells_list", null) ?: return
        val type = object : TypeToken<MutableList<Spell>>() {}.type
        spells = gson.fromJson(json, type)
    }

    private fun saveRecipes() {
        sharedPreferences.edit().apply {
            putString("recipes_list", gson.toJson(spells))
            apply()
        }
    }

    fun getSpells(): MutableList<Spell> = spells
}