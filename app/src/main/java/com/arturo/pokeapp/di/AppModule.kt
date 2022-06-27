package com.arturo.pokeapp.di

import com.arturo.pokeapp.ui.fragments.abilities.AbilitiesAdapter
import com.arturo.pokeapp.ui.fragments.evolutionchain.EvolutionChainAdapter
import com.arturo.pokeapp.ui.fragments.pokemonlist.PokemonListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.random.Random

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonListAdapter() : PokemonListAdapter {
        return PokemonListAdapter()
    }

    @Provides
    @Singleton
    fun provideAbilitiesAdapter() : AbilitiesAdapter {
        return AbilitiesAdapter()
    }

    @Provides
    @Singleton
    fun provideEvolutionChainAdapter() : EvolutionChainAdapter {
        return EvolutionChainAdapter()
    }

    @Provides
    fun provideFavoriteResponse() : Boolean {
        return Random.nextBoolean()
    }
}