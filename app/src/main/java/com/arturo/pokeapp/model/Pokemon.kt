package com.arturo.pokeapp.model

import com.arturo.pokeapp.model.db.entities.PokemonEntity
import com.arturo.pokeapp.model.network.response.PokemonResponse

data class Pokemon(
    val name : String
)

fun PokemonResponse.toPokemon() = Pokemon(name)
fun PokemonEntity.toPokemon() = Pokemon(name)