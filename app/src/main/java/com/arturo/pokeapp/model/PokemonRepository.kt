package com.arturo.pokeapp.model

import com.arturo.pokeapp.model.db.dao.PokemonDao
import com.arturo.pokeapp.model.db.entities.toDatabase
import com.arturo.pokeapp.model.network.PokemonService
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService,
    private val pokemonDao: PokemonDao
) {

    private suspend fun getAllPokemonFromApi() : List<Pokemon> {
        val response = api.getPokemonList()
        return response.map { it.toPokemon() }
    }

    private suspend fun getAllPokemonFromDatabase() : List<Pokemon> {
        val response = pokemonDao.getAllPokemons()
        return response.map { it.toPokemon() }
    }

    suspend fun getAllPokemon() : List<Pokemon> {
        val pokemons = getAllPokemonFromApi()

        return if (pokemons.isNotEmpty()) {
            pokemonDao.deleteAll()
            pokemonDao.insertAll(pokemons.map { it.toDatabase() })
            pokemons
        } else {
            getAllPokemonFromDatabase()
        }
    }
}