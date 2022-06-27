package com.arturo.pokeapp.model.network.response

import com.google.gson.annotations.SerializedName

data class AbilitiesResponse (
    @SerializedName("abilities") var abilities : ArrayList<Ability>
)

data class Ability (
    @SerializedName("ability") var ability: AbilityInfo
)

data class AbilityInfo (
    @SerializedName("name") var name : String
)