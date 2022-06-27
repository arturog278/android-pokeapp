package com.arturo.pokeapp.model.network

import com.arturo.pokeapp.model.network.response.AbilitiesResponse
import com.arturo.pokeapp.model.network.response.EvolutionChainResponse
import com.arturo.pokeapp.model.network.response.PokemonListResponse
import com.arturo.pokeapp.model.network.response.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonApiClient {
    @GET("pokemon?limit=151")
    suspend fun getPokemonList() : Response<PokemonListResponse>

    @GET
    suspend fun getPokemonInfo(@Url url: String) : Response<PokemonSpeciesResponse>

    @GET
    suspend fun getEvolutionChain(@Url url: String) : Response<EvolutionChainResponse>

    @GET
    suspend fun getAbilities(@Url url: String) : Response<AbilitiesResponse>
}