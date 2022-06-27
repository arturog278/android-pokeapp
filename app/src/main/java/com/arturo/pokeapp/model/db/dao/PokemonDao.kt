package com.arturo.pokeapp.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arturo.pokeapp.model.db.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    suspend fun getAllPokemons(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons : List<PokemonEntity>)

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteAll()
}