package com.example.mobilespellbook

interface OnInteractionListener {
    fun onSpellSelected(spell: Spell, index: Int)
    fun onSpellAdded(newSpell: Spell)
    fun onSpellDeleted(index: Int)
}