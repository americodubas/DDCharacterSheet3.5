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
 * Service responsible for the [Character]
 */
class CharacterService(val context: Context) {

    private val characterDao = CharacterDao(context)

    fun getCharacter(id: String): Character {
        return characterDao.getCharacter(id.toInt())
    }

    fun getAllCharacters(): List<CharacterDto> {
        return convertFromTo(characterDao.getAllCharacters())
    }

    fun insertEmptyCharacter(): Int {
        //TODO create blank char
        var character = Character()
        character.name = "Hizagkuur"
        character.race = getDefaultRace()
        character = characterDao.insertCharacter(character)

        val characterClass = CharacterClasses()
        characterClass.character = character
        characterClass.classes = getDefaultClass()
        characterClass.level = 1

        characterDao.insertCharacterClass(characterClass)
        return character.id
    }

    private fun getDefaultRace() = characterDao.getRace(2)

    private fun getDefaultClass() = characterDao.getClasses(1)

    fun updateCharacter(character: Character) {
        characterDao.updateCharacter(character)
    }

    fun getAllRaces(): List<Race> {
        return characterDao.getAllRaces()
    }

    fun getAllClasses(): List<Classes> {
        return characterDao.getAllClasses()
    }

    fun getNotUsedClasses(character: Character): List<Classes> {
        val allClasses = getAllClasses()
        val usedClassesId = TreeSet<String>()
        character.characterClasses.forEach { usedClassesId.add(it.classes.id.toString()) }
        return allClasses.filter { !usedClassesId.contains(it.id.toString()) }
    }

    fun getCharacterClasses(id: String): List<Classes> {
        return characterDao.getCharacterClasses(id.toInt())
    }

    fun insertCharacterClasses(characterClasses: CharacterClasses){
        characterDao.insertCharacterClass(characterClasses)
    }

    fun deleteCharacterClasses(characterClasses: CharacterClasses){
        characterDao.deleteCharacterClass(characterClasses)
    }

    fun increaseCharacterClassesLevel(characterClasses: CharacterClasses) : CharacterClasses {
        characterClasses.level += 1
        characterDao.updateCharacterClass(characterClasses)
        return characterClasses
    }

    fun decreaseCharacterClassesLevel(characterClasses: CharacterClasses) : CharacterClasses {
        if (!canDecreaseLevel(characterClasses)) throw CannotDecreaseCharacterClassesLevel()
        characterClasses.level -= 1
        characterDao.updateCharacterClass(characterClasses)
        return characterClasses
    }

    private fun canDecreaseLevel(characterClasses: CharacterClasses) = characterClasses.level > 1

}