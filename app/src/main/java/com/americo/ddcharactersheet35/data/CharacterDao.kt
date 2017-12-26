package com.americo.ddcharactersheet35.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.content.Context
import com.americo.ddcharactersheet35.model.Character

/**
 * Created by Americo on 02/05/2017.
 *
 * DAO responsible for the [Character]
 */
@Dao
interface CharacterDao {

    @Query("select * from character")
    fun getAllCharacters(): List<Character>

    @Query("select * from character where _id = :id")
    fun getCharacter(id: Long): Character

    @Insert
    fun insertCharacter(character: Character): Long

    @Update
    fun updateCharacter(character: Character)

}