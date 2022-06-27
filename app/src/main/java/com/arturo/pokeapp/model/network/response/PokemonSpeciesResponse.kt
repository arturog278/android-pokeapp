package com.arturo.pokeapp.model.network.response

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse (
    @SerializedName("base_happiness") var baseHappiness : Int,
    @SerializedName("capture_rate") var captureRate : Int,
    @SerializedName("color") var color : Color,
    @SerializedName("egg_groups") var eggGroups : ArrayList<EggGroups>,
    @SerializedName("evolution_chain") var evolutionChain : EvolutionChain
)

data class Color (
    @SerializedName("name") var name : String
)

data class EggGroups (
    @SerializedName("name") var name : String
)

data class EvolutionChain (
    @SerializedName("url") var url : String
)