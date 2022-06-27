package com.arturo.pokeapp.model.network.response

import com.google.gson.annotations.SerializedName

data class PokemonListResponse (
    @SerializedName("results") var pokemonList  : ArrayList<PokemonResponse> = arrayListOf()
)

data class PokemonResponse (
    @SerializedName("name") var name : String
)
