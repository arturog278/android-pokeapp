package com.arturo.pokeapp.model.network

import com.arturo.pokeapp.model.network.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class PokemonService @Inject constructor(private val api: PokemonApiClient) {

    suspend fun getPokemonList() : List<PokemonResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPokemonList()
                response.body()?.pokemonList!!
            } catch (e: Exception) {
                emptyList()
            }

        }
    }

    suspend fun getPokemonInfo(name : String) : PokemonSpeciesResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPokemonInfo("pokemon-species/$name")
                response.body()
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun getEvolutionChain(url : String) : EvolutionChainResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getEvolutionChain(url)
                response.body()
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun getAbilities(name : String) : AbilitiesResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAbilities("pokemon/$name")
                response.body()
            } catch (e: Exception) {
                null
            }
        }
    }
}