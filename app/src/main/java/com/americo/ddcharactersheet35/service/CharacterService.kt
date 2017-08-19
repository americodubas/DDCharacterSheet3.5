package com.americo.ddcharactersheet35.service

import android.content.Context
import com.americo.ddcharactersheet35.data.CharacterDao
import com.americo.ddcharactersheet35.dto.CharacterDto
import com.americo.ddcharactersheet35.exception.CannotDecreaseCharacterClassesLevel
import com.americo.ddcharactersheet35.model.Character
import com.americo.ddcharactersheet35.model.CharacterClasses
import com.americo.ddcharactersheet35.model.Classes
import com.americo.ddcharactersheet35.model.Race
import com.americo.ddcharactersheet35.util.convertFromTo
import java.util.*

/**
 * Created by Americo on 27/05/2017.
 *
 * Service responsible for the [Character].
 */
class CharacterService(val context: Context) {

    private val characterDao = CharacterDao(context)

    /**
     * Returns a [Character] by [id].
     */
    fun getCharacter(id: String): Character {
        return characterDao.getCharacter(id.toInt())
    }

    /**
     * Returns a [List] of all [CharacterDto].
     */
    fun getAllCharacters(): List<CharacterDto> {
        return convertFromTo(characterDao.getAllCharacters())
    }

    /**
     * Insert an empty [Character] with default values.
     */
    fun insertEmptyCharacter(): Int {
        var character = Character()
        character.name = "Unknown"
        character.race = getDefaultRace()
        character = characterDao.insertCharacter(character)

        val characterClass = CharacterClasses()
        characterClass.character = character
        characterClass.classes = getDefaultClass()
        characterClass.level = 1

        characterDao.insertCharacterClass(characterClass)
        return character.id
    }

    /**
     * Returns the default [Race].
     */
    private fun getDefaultRace() = characterDao.getRace(2)

    /**
     * Returns the default [Class].
     */
    private fun getDefaultClass() = characterDao.getClasses(1)

    /**
     * Update the [Character].
     */
    fun updateCharacter(character: Character) {
        characterDao.updateCharacter(character)
    }

    /**
     * Returns a [List] of all [Race].
     */
    fun getAllRaces(): List<Race> {
        return characterDao.getAllRaces()
    }

    /**
     * Returns a [List] of all [Classes].
     */
    fun getAllClasses(): List<Classes> {
        return characterDao.getAllClasses()
    }

    /**
     * Returns a [List] of the [Classes] not used by the [Character].
     */
    fun getNotUsedClasses(character: Character): List<Classes> {
        val allClasses = getAllClasses()
        val usedClassesId = TreeSet<String>()
        character.characterClasses.forEach { usedClassesId.add(it.classes.id.toString()) }
        return allClasses.filter { !usedClassesId.contains(it.id.toString()) }
    }

    /**
     * Returns a [List] of [Classes] of the [Character] by it's [id].
     */
    fun getCharacterClasses(id: String): List<Classes> {
        return characterDao.getCharacterClasses(id.toInt())
    }

    /**
     * Insert the relation of [CharacterClasses].
     */
    fun insertCharacterClasses(characterClasses: CharacterClasses){
        characterDao.insertCharacterClass(characterClasses)
    }

    /**
     * Delete the relation of [CharacterClasses].
     */
    fun deleteCharacterClasses(characterClasses: CharacterClasses){
        characterDao.deleteCharacterClass(characterClasses)
    }

    /**
     * Increase the level of the [CharacterClasses] by + 1.
     */
    fun increaseCharacterClassesLevel(characterClasses: CharacterClasses) : CharacterClasses {
        characterClasses.level += 1
        characterDao.updateCharacterClass(characterClasses)
        return characterClasses
    }

    /**
     * Decrease the level of the [CharacterClasses] by - 1.
     * The level won't be decreased if the [CharacterClasses] level is 1,
     * a [CannotDecreaseCharacterClassesLevel] will be throw.
     */
    fun decreaseCharacterClassesLevel(characterClasses: CharacterClasses) : CharacterClasses {
        if (!canDecreaseLevel(characterClasses)) throw CannotDecreaseCharacterClassesLevel()
        characterClasses.level -= 1
        characterDao.updateCharacterClass(characterClasses)
        return characterClasses
    }

    /**
     * Verify if the [CharacterClasses] can be decreased, the minimum level is 1.
     */
    private fun canDecreaseLevel(characterClasses: CharacterClasses) = characterClasses.level > 1

}