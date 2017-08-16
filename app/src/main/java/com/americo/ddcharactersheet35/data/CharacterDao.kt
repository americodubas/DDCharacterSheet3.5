package com.americo.ddcharactersheet35.data

import android.content.Context
import com.americo.ddcharactersheet35.model.Character
import com.americo.ddcharactersheet35.model.CharacterClasses
import com.americo.ddcharactersheet35.model.Classes
import com.americo.ddcharactersheet35.model.Race

/**
 * Created by Americo on 02/05/2017.
 */

class CharacterDao(context: Context) {

    private val db = DatabaseHelper(context)

    fun getAllCharacters(): List<Character> {
        return db.getCharacterDao().queryForAll()
    }

    fun getCharacter(id: Int): Character {
        return db.getCharacterDao().queryForId(id)
    }

    fun  getRace(id: Int): Race {
        return db.getRaceDao().queryForId(id)
    }

    fun  getClasses(id: Int): Classes {
        return db.getClassesDao().queryForId(id)
    }

    fun getCharacterClasses(id: Int): List<Classes> {
        val characterClassesBuilder = db.getCharacterClassesDao().queryBuilder()
        val classesBuilder = db.getClassesDao().queryBuilder()

        characterClassesBuilder.where().eq("character_id", id)
        return classesBuilder.join(characterClassesBuilder).query()
    }

    fun insertCharacter(character: Character): Character {
        db.getCharacterDao().create(character)
        return character
    }

    fun insertCharacterClass(characterClasses: CharacterClasses) {
        db.getCharacterClassesDao().create(characterClasses)
    }

    fun deleteCharacterClass(characterClasses: CharacterClasses) {
        db.getCharacterClassesDao().delete(characterClasses)
    }

    fun updateCharacterClass(characterClasses: CharacterClasses) {
        db.getCharacterClassesDao().update(characterClasses)
    }

    fun updateCharacter(character: Character) {
        db.getCharacterDao().update(character)
    }

    fun getAllRaces(): List<Race> {
        return db.getRaceDao().queryForAll()
    }

    fun getAllClasses(): List<Classes> {
        return db.getClassesDao().queryForAll()
    }
}