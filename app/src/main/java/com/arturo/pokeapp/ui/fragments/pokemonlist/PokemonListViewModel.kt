package com.arturo.pokeapp.ui.fragments.pokemonlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arturo.pokeapp.model.Pokemon
import com.arturo.pokeapp.model.PokemonRepository
import com.arturo.pokeapp.model.network.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val pokemonList = MutableLiveData<List<Pokemon>>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = repository.getAllPokemon()
            pokemonList.postValue(result)
            isLoading.postValue(false)
        }
    }

}