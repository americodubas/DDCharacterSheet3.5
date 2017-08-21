package com.americo.ddcharactersheet35.data

import android.content.Context
import com.americo.ddcharactersheet35.model.CharacterClasses
import com.americo.ddcharactersheet35.model.Classes

/**
 * Created by Americo on 20/08/2017.
 *
 * DAO responsible for the [CharacterClasses].
 */
class CharacterClassesDao(context: Context) {

    private val db = DatabaseHelper(context)

    /**
     * Returns a [List] of [CharacterClasses] from the character.
     */
    fun getCharacterClasses(id: Int): List<Classes> {
        val characterClassesBuilder = db.getCharacterClassesDao().queryBuilder()
        val classesBuilder = db.getClassesDao().queryBuilder()

        characterClassesBuilder.where().eq("character_id", id)
        return classesBuilder.join(characterClassesBuilder).query()
    }

    /**
     * Insert the relation of [CharacterClasses].
     */
    fun insertCharacterClass(characterClasses: CharacterClasses) {
        db.getCharacterClassesDao().create(characterClasses)
    }

    /**
     * Delete the relation of [CharacterClasses].
     */
    fun deleteCharacterClass(characterClasses: CharacterClasses) {
        db.getCharacterClassesDao().delete(characterClasses)
    }

    /**
     * Update the relation of [CharacterClasses].
     */
    fun updateCharacterClass(characterClasses: CharacterClasses) {
        db.getCharacterClassesDao().update(characterClasses)
    }
}