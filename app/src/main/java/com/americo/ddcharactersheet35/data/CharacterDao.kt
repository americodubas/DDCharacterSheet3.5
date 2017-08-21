package com.americo.ddcharactersheet35.data

import android.content.Context
import com.americo.ddcharactersheet35.model.Character

/**
 * Created by Americo on 02/05/2017.
 *
 * DAO responsible for the [Character]
 */
class CharacterDao(context: Context) {

    private val db = DatabaseHelper(context)

    fun getAllCharacters(): List<Character> {
        return db.getCharacterDao().queryForAll()
    }

    fun getCharacter(id: Int): Character {
        return db.getCharacterDao().queryForId(id)
    }

    fun insertCharacter(character: Character): Character {
        db.getCharacterDao().create(character)
        return character
    }

    fun updateCharacter(character: Character) {
        db.getCharacterDao().update(character)
    }
}