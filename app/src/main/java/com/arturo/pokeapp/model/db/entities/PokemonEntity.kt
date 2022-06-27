package com.arturo.pokeapp.model.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arturo.pokeapp.model.Pokemon

@Entity(tableName = "pokemon_table")
data class PokemonEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name : String
)

fun Pokemon.toDatabase() = PokemonEntity(name = name)