package com.arturo.pokeapp.model.network.response

import com.google.gson.annotations.SerializedName

data class EvolutionChainResponse (
    @SerializedName("chain") var chain : Chain
)

data class Chain (
    @SerializedName("species") var pokemon : Species,
    @SerializedName("evolves_to") var evolvesTo : ArrayList<Chain>
)

data class Species (
    @SerializedName("name") var name : String
)