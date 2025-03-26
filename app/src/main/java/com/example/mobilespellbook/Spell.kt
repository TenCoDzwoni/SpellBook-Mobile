package com.example.mobilespellbook

import java.io.Serializable

data class Spell(
    var name: String = "",
    var cost: String = "",
    var function: String = "",
    var rating: Float = 0f
) : Serializable