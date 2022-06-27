package com.arturo.pokeapp.ui.fragments.pokemoninfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturo.pokeapp.model.network.PokemonService
import com.arturo.pokeapp.model.network.response.PokemonSpeciesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val api: PokemonService
) : ViewModel() {
    val pokemonInfo = MutableLiveData<PokemonSpeciesResponse?>()

    fun onCreate(pokemonName: String) {
        viewModelScope.launch {
            val result = api.getPokemonInfo(pokemonName)
            pokemonInfo.postValue(result)
        }
    }
}