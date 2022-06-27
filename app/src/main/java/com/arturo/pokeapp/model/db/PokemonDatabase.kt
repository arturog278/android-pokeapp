package com.arturo.pokeapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arturo.pokeapp.model.db.dao.PokemonDao
import com.arturo.pokeapp.model.db.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao
}