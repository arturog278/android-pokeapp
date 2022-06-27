package com.arturo.pokeapp.ui.fragments.abilities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturo.pokeapp.model.network.PokemonService
import com.arturo.pokeapp.model.network.response.Ability
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AbilitiesViewModel @Inject constructor(
    private val api: PokemonService
) : ViewModel() {
    val abilities = MutableLiveData<List<Ability>>()

    fun onCreate(pokemonName: String) {
        viewModelScope.launch {
            val result = api.getAbilities(pokemonName)
            abilities.postValue(result?.abilities)
        }
    }
}